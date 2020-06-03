/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Enemy.Enemy_Square;
import GameState.GameState;
import GameState.Stage2;
import QuestImg.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.sql.*;

/**
 *
 * @author Devisate
 */
public class QuestionDAO {

    public int count = 0;
    Connection conexao;
    Usuario usuario;
    Font f;
    public String QuestDesc = "";
    int QuestID;
    public int imgID;
    public int[] Correct = new int[4];
    public String[] alternatives = new String[4];
    int mX, mY;

    public QuestionDAO() throws SQLException {
        conexao = Conexao.getConexao();
        usuario = new Usuario();
    }

    public void showQuestion() throws SQLException {

        try {
            String sql = "select * from question order by rand() limit 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                QuestID = rs.getInt("quest_id");
            }
            String sql1 = "select * from store where quest_id = ? and usu_id = ?";
            PreparedStatement ps1 = conexao.prepareStatement(sql1);
            ps1.setInt(1, QuestID);
            ps1.setInt(2, usuario.getId());

            ResultSet rs1 = ps1.executeQuery();
            if (!rs1.next()) {

                QuestDesc = rs.getString("quest_desc");
                showAnswer();

            } else {
                showQuestion();
            }
        } catch (Exception e) {
            throw new SQLException("Erro: " + e.getMessage());
        }
    }

    public Rectangle QuestionP() {
        return new Rectangle(220, 10, 330, 215);
    }

    public Rectangle QuestionR() {
        return new Rectangle(250, 20, 250, 30);
    }

    public Rectangle Timer() {
        return new Rectangle(505, 20, 30, 30);
    }

    public Rectangle Alt1() {
        return new Rectangle(250, 60, 250, 30);
    }

    public Rectangle Alt2() {
        return new Rectangle(250, 100, 250, 30);
    }

    public Rectangle Alt3() {
        return new Rectangle(250, 140, 250, 30);
    }

    public Rectangle Alt4() {
        return new Rectangle(250, 180, 250, 30);
    }

    public void draw(Graphics2D g) {

    }

    public void showAnswer() throws SQLException {
        int NextCount = 0;
        try {

            String sql = "select * from answer where quest_id = ? order by rand()";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, QuestID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                alternatives[NextCount] = rs.getString("answer_desc");
                Correct[NextCount] = rs.getInt("answer_correct");
                NextCount++;
            }

        } catch (Exception e) {
            throw new SQLException("Erro: " + e.getMessage());
        }

    }

    public void Correct(int a) {
        if (a == 1) {

            try {

                GameState.player.setAtack(true);

                String sql = "insert into store(usu_id, quest_id,acertou)values(?,?,?)";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, usuario.getId());
                ps.setInt(2, QuestID);
                ps.setInt(3, 1);
                ps.execute();
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            Enemy_Square.count--;
            GameState.player.hito = true;
            GameState.player.getLifebar().damage(10);
            try {
                String sql = "insert into store(usu_id, quest_id,acertou)values(?,?,?)";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, usuario.getId());
                ps.setInt(2, QuestID);
                ps.setInt(3, 0);
                ps.execute();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        GameState.quizz = false;
        GameState.frami = false;
    }

    public void SelectImage() {
        try {
            String sql = "select * from question where quest_id >= 9000 order by rand() limit 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                QuestID = rs.getInt("quest_id");
            }
            String sql1 = "select * from store where quest_id = ?";
            PreparedStatement ps1 = conexao.prepareStatement(sql1);
            ps1.setInt(1, QuestID);
            ResultSet rs1 = ps1.executeQuery();
            if (!rs1.next()) {
                System.out.println(rs.getInt("quest_id"));
                imgID = rs.getInt("quest_id");
                System.out.println(imgID);
                if(imgID == 9000){
                    Imagem1 img1 = new Imagem1();
                    img1.setVisible(true);
                } else if(imgID == 9001){
                    Imagem2 img2 = new Imagem2();
                    img2.setVisible(true);
                } else if(imgID == 9002){
                    Imagem3 img3 = new Imagem3();
                    img3.setVisible(true);
                }
            } else {
                SelectImage();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void CorrectImage(int b, int num) {
                Stage2.quizz2 = false;

        if (b == 1) {
            try {
                GameState.player.setAtack(true);
                String sql = "insert into store(usu_id, quest_id,acertou)values(?,?,?)";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, usuario.getId());
                ps.setInt(2, num);
                ps.setInt(3, 1);
                ps.execute();
            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            GameState.player.getLifebar().damage(100);
            try {
                String sql = "insert into store(usu_id, quest_id,acertou)values(?,?,?)";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setInt(1, usuario.getId());
                ps.setInt(2, 9000);
                ps.setInt(3, 0);
                ps.execute();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Stage2.quizz2 = false;
    }

    public void onClick() {
        if ((mX > Alt1().x && mX < (Alt1().x + Alt1().width))
                && (mY > Alt1().y && mY < (Alt1().y + Alt1().height))) {
            if (Correct[0] == 1) {
                Correct(1);
            } else {
                Correct(0);
            }

        }
        if ((mX > Alt2().x && mX < (Alt2().x + Alt2().width))
                && (mY > Alt2().y && mY < (Alt2().y + Alt2().height))) {
            if (Correct[1] == 1) {
                Correct(1);
            } else {
                Correct(0);
            }

        }
        if ((mX > Alt3().x && mX < (Alt3().x + Alt3().width))
                && (mY > Alt3().y && mY < (Alt3().y + Alt3().height))) {
            if (Correct[2] == 1) {
                Correct(1);
            } else {
                Correct(0);
            }

        }
        if ((mX > Alt4().x && mX < (Alt4().x + Alt4().width))
                && (mY > Alt4().y && mY < (Alt4().y + Alt4().height))) {
            if (Correct[3] == 1) {
                Correct(1);
            } else {
                Correct(0);
            }

        }

    }

}
