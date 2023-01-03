package org.jonathan.blogPessoal.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;


@Entity //transforma em tabela 
@Table(name="tb_postagens") //nomeia a tabela
public class Postagem {
	
	@Id //informa que é um ID
	@GeneratedValue(strategy=GenerationType.IDENTITY) //auto increment
	private Long id; //long igual ao int 
	
	@Size(min=4, max=50) // define um tamanho max e min de caracteres pro campo de texto
	private String texto; 
	
//pode passar varias notacoes como o not null
	
	@NotNull // seguir no banco dados a mesma forma que esta aqui 
	private String titulo;
	

	
	
	@UpdateTimestamp //formatar de acordo com a data do computador
	private LocalDateTime data;
	
	@ManyToOne
    @JsonIgnoreProperties("postagem")
    private Tema tema;

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}