package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.Autentica;
import br.com.fiap.trataderma.domain.entity.Consulta;
import br.com.fiap.trataderma.domain.entity.UnidadeHospitalar;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ConsultaRepository implements Repository<Consulta, Long> {

    private PacienteRepository pacienteRepository = PacienteRepository.build();
    private UnidadeHospitalarRepository unidadeHospitalarRepository = UnidadeHospitalarRepository.build();

    private ConnectionFactory factory;

    private static final AtomicReference<ConsultaRepository> instance = new AtomicReference<>();

    private ConsultaRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static ConsultaRepository build() {
        instance.compareAndSet( null, new ConsultaRepository() );
        return instance.get();
    }

    @Override
    public List<Consulta> findAll() {

        List<Consulta> consultas = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_CONSULTA";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var consulta = buildConsulta (resultSet);
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }

        return consultas;
    }

    @Override
    public Consulta findById(Long id) {

        Consulta consulta = null;

        var sql = "SELECT * FROM T_TD_CONSULTA WHERE ID_CONSULTA = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    consulta = buildConsulta(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return consulta;

    }

    @Override
    public Consulta persist(Consulta consulta) {

        var sql = "INSERT INTO T_TD_CONSULTA (id_consulta, dt_hr_consulta, tel_central, id_paciente, id_unid_hospitalar) values (seq_consulta.nextval,?,?,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_consulta"});
            preparedStatement.setDate(1, Date.valueOf(consulta.getConsulta().toLocalDate()));
            preparedStatement.setString(2, consulta.getTelefoneCentral());
            preparedStatement.setLong(3, consulta.getPaciente().getId());
            preparedStatement.setLong(4, consulta.getUnidadeHospitalar().getId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                consulta.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return consulta;
    }

    private Consulta buildConsulta(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_consulta");
        var dataHoraConsulta = resultSet.getDate("dt_hr_consulta").toInstant();
        var telCentral = resultSet.getString("tel_central");
        var idPaciente = resultSet.getLong("id_paciente");
        var idUnidHospitalar = resultSet.getLong("id_unid_hospitalar");

        var paciente = pacienteRepository.findById(idPaciente);
        var unidHospitalar = unidadeHospitalarRepository.findById(idUnidHospitalar);

        var consulta = new Consulta(id, LocalDateTime.ofInstant(dataHoraConsulta, ZoneOffset.UTC), telCentral, paciente, unidHospitalar);

        return consulta;
    }
}
