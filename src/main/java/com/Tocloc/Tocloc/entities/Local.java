package com.Tocloc.Tocloc.entities;

import com.Tocloc.Tocloc.entities.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Local")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "endereco", nullable = false)
    private String endereco;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "tipoEspaco", nullable = false)
    private String tipoEspaco;
    @Column(name = "capacidade")
    private int capacidade;
    @Column(name = "precoPorHora")
    private double precoPorHora;
    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    private User proprietario;
    @ManyToOne
    @JoinColumn(name = "usuario_locador_id", nullable = true)
    private User usuarioLocador;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTipoEspaco() {
        return tipoEspaco;
    }
    public void setTipoEspaco(String tipoEspaco) {
        this.tipoEspaco = tipoEspaco;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public double getPrecoPorHora() {
        return precoPorHora;
    }
    public void setPrecoPorHora(double precoPorHora) {
        this.precoPorHora = precoPorHora;
    }
    public User getProprietario() {
        return proprietario;
    }
    public void setProprietario(User proprietario) {
        this.proprietario = proprietario;
    }
    public User getUsuarioLocador() {
        return usuarioLocador;
    }
    public void setUsuarioLocador(User usuarioLocador) {
        this.usuarioLocador = usuarioLocador;
    }
    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipoEspaco='" + tipoEspaco + '\'' +
                ", capacidade=" + capacidade +
                ", precoPorHora=" + precoPorHora +
                ", proprietario=" + proprietario +
                ", usuarioLocador=" + (usuarioLocador != null ? usuarioLocador.getId() : "null") +
                '}';
    }
}
