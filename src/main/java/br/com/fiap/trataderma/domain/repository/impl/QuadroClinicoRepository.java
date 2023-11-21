package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.entity.QuadroClinico;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class QuadroClinicoRepository implements Repository<QuadroClinico, Long> {

    private PacienteRepository pacienteRepository = PacienteRepository.build();

    private ConnectionFactory factory;

    private static final AtomicReference<QuadroClinicoRepository> instance = new AtomicReference<>();

    private QuadroClinicoRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static QuadroClinicoRepository build() {
        instance.compareAndSet( null, new QuadroClinicoRepository() );
        return instance.get();
    }

    @Override
    public List<QuadroClinico> findAll() {

        List<QuadroClinico> quadroClinicos = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_QUADRO_CLINICO";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var quadroClinico = buildQuadroClinico (resultSet);
                    quadroClinicos.add(quadroClinico);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }
        return quadroClinicos;
    }

    @Override
    public QuadroClinico findById(Long id) {

        QuadroClinico quadroClinico = null;

        var sql = "SELECT * FROM T_TD_QUADRO_CLINICO WHERE ID_QUADRO_CLINICO = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    quadroClinico = buildQuadroClinico(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return quadroClinico;
    }

    @Override
    public QuadroClinico persist(QuadroClinico quadroClinico) {
        var sql = "INSERT INTO T_TD_QUADRO_CLINICO (id_quadro_clinico, ds_alergias, id_paciente) values (seq_quadro_clinico.nextval,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_quadro_clinico"});
            preparedStatement.setString(1, quadroClinico.getDescricaoAlergias());
            preparedStatement.setLong(2, quadroClinico.getPaciente().getId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                quadroClinico.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return quadroClinico;
    }

    private QuadroClinico buildQuadroClinico(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_quadro_clinico");
        var alergias = resultSet.getString("ds_alergias");
        var idPaciente = resultSet.getLong("id_paciente");

        var paciente = pacienteRepository.findById(idPaciente);


        var quadroClinico = new QuadroClinico(id, alergias, paciente);

        return quadroClinico;
    }
}


