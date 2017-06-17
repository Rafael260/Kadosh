package org.itbparnamirim.kadosh6.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Geraldo e Rafael
 */
@Entity
public class Membro implements Serializable {

    @GeneratedValue
    @Id
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;
    private String usuario;
    private String senha;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataBatismoApresentacao;

    private Boolean lider;
    private Boolean tesoureiro;
    private Boolean professor;
    private Boolean administrador;

    @ManyToOne
    private Grupo grupo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Ministerio> ministerios;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Turma> turmas;
    
    @OneToMany
    private List<Matricula> matriculas;

    public Membro() {
        nome = "";
        telefone = "";
        email = "";
        dataNascimento = null;
        usuario = "";
        senha = "";
        dataBatismoApresentacao = null;
        grupo = null;
        lider = false;
        tesoureiro = false;
        professor = false;
        administrador = false;
        ministerios = new ArrayList<>();
        turmas = new ArrayList<>();
    }

    public Membro(Integer id, String nome, String telefone, String email, Date dataNascimento, String usuario, String senha, Date dataBatismoApresentacao, Grupo grupo, List<Turma> turmas, Boolean lider, Boolean tesoureiro, Boolean professor, Boolean administrador, List<Ministerio> ministerios) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.usuario = usuario;
        this.senha = senha;
        this.dataBatismoApresentacao = dataBatismoApresentacao;
        this.grupo = grupo;
        this.turmas = turmas;
        this.lider = lider;
        this.tesoureiro = tesoureiro;
        this.professor = professor;
        this.administrador = administrador;
        this.ministerios = ministerios;
    }

    public Boolean getLider() {
        return lider;
    }

    public void setLider(Boolean lider) {
        this.lider = lider;
    }

    public Boolean getTesoureiro() {
        return tesoureiro;
    }

    public void setTesoureiro(Boolean tesoureiro) {
        this.tesoureiro = tesoureiro;
    }

    public Boolean getProfessor() {
        return professor;
    }

    public void setProfessor(Boolean professor) {
        this.professor = professor;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataBatismoApresentacao() {
        return dataBatismoApresentacao;
    }

    public void setDataBatismoApresentacao(Date dataBatismoApresentacao) {
        this.dataBatismoApresentacao = dataBatismoApresentacao;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Ministerio> getMinisterios() {
        return ministerios;
    }

    public void setMinisterios(List<Ministerio> ministerios) {
        this.ministerios = ministerios;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
    
    

    public void adicionarMinisterio(Ministerio ministerio) {
        this.ministerios.add(ministerio);
    }

    public void removerMinisterio(Ministerio ministerio) {
        this.ministerios.remove(ministerio);
    }

    @Override
    public boolean equals(Object outro) {
        if (!(outro instanceof Membro)) {
            return false;
        }
        Membro outroMembro = (Membro) outro;
        if (this.id == null || outroMembro.getId() == null) {
            return false;
        }
        return this == outroMembro || this.id.equals(outroMembro.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return nome;
    }
}
