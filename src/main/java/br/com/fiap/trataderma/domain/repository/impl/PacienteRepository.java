package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.EnderecoPaciente;
import br.com.fiap.trataderma.domain.entity.Paciente;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PacienteRepository implements Repository<Paciente, Long> {

    private AutenticaRepository autenticaRepository = AutenticaRepository.build();

    private ConnectionFactory factory;

    private static final AtomicReference<PacienteRepository> instance = new AtomicReference<>();

    private PacienteRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static PacienteRepository build() {
        instance.compareAndSet( null, new PacienteRepository() );
        return instance.get();
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> pacientes = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_PACIENTE";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var paciente = buildPaciente (resultSet);
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }

        return pacientes;
    }

    @Override
    public Paciente findById(Long id) {

        Paciente paciente = null;

        var sql = "SELECT * FROM T_TD_PACIENTE WHERE ID_PACIENTE = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    paciente = buildPaciente(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return paciente;
    }

    public Paciente findByIdAutentica(Long id) {

        Paciente paciente = null;

        var sql = "SELECT * FROM T_TD_PACIENTE WHERE ID_AUTENTICA = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    paciente = buildPaciente(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return paciente;
    }

    @Override
    public Paciente persist(Paciente paciente) {
        var sql = "INSERT INTO T_TD_PACIENTE (id_paciente, nm_paciente, nr_cpf, nr_rg, dt_nascimento, fl_sexo, tip_grupo_sanguineo, id_autentica) values (seq_paciente.nextval,?,?,?,?,?,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = paciente.getDataNascimento().format(dateTimeFormatter);

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_paciente"});
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, String.valueOf(paciente.getCpf()));
            preparedStatement.setString(3, paciente.getRg());
            preparedStatement.setString(4, formattedDate);
            preparedStatement.setString(5, paciente.getSexo());
            preparedStatement.setString(6, paciente.getGrupoSanguineo());
            preparedStatement.setLong(7, paciente.getAutentica().getId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                paciente.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return paciente;
    }

    private Paciente buildPaciente(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_paciente");
        var nome = resultSet.getString("nm_paciente");
        var cpf = resultSet.getString("nr_cpf");
        var rg = resultSet.getString("nr_rg");
        var dataNascimento = resultSet.getDate("dt_nascimento").toLocalDate();
        var sexo = resultSet.getString("fl_sexo");
        var grupoSanguineo = resultSet.getString("tip_grupo_sanguineo");
        var idAutentica = resultSet.getLong("id_autentica");

        var autentica = autenticaRepository.findById(idAutentica);


        var paciente = new Paciente(id, nome, cpf, rg, dataNascimento, sexo, grupoSanguineo, autentica);

        return paciente;
    }
}
