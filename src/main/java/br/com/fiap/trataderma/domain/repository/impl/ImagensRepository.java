package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.EnderecoPaciente;
import br.com.fiap.trataderma.domain.entity.Imagens;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ImagensRepository implements Repository<Imagens, Long> {


    private PacienteRepository pacienteRepository = PacienteRepository.build();

    private ConnectionFactory factory;

    private static final AtomicReference<ImagensRepository> instance = new AtomicReference<>();

    private ImagensRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static ImagensRepository build() {
        instance.compareAndSet( null, new ImagensRepository() );
        return instance.get();
    }

    @Override
    public List<Imagens> findAll() {

        List<Imagens> listaImagens = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_IMAGENS";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var imagens = buildImagens (resultSet);
                    listaImagens.add(imagens);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }

        return listaImagens;
    }

    @Override
    public Imagens findById(Long id) {

        Imagens imagens = null;

        var sql = "SELECT * FROM T_TD_IMAGENS WHERE ID_IMAGEM = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    imagens = buildImagens(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return imagens;
    }

    @Override
    public Imagens persist(Imagens imagens) {
        var sql = "INSERT INTO T_TD_IMAGENS (id_imagem, link_url, dt_envio, id_paciente) values (seq_imagem.nextval,?,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_imagem"});
            preparedStatement.setString(1, imagens.getLinkUrl());
            preparedStatement.setDate(2, Date.valueOf(imagens.getDataEnvio()));
            preparedStatement.setLong(3, imagens.getPaciente().getId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                imagens.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return imagens;
    }

    private Imagens buildImagens(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_imagem");
        var linkUrl = resultSet.getString("link_url");
        var dataEnvio = resultSet.getDate("dt_envio").toLocalDate();
        var idPaciente = resultSet.getLong("id_paciente");

        var paciente = pacienteRepository.findById(idPaciente);

        var imagens = new Imagens(id, linkUrl, dataEnvio, paciente);

        return imagens;
    }
}
