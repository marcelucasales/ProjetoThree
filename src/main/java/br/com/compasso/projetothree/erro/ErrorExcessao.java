package br.com.compasso.projetothree.erro;

public class ErrorExcessao {
    private Integer status_code;
    private String message;

    public ErrorExcessao() {}

    public ErrorExcessao(Integer status_code) {
        this.status_code = status_code;
        this.message = "Preencha todos os campos do produto e o preço precisa ser positivo. {nome, descrição e preço}";
    }

    public String getMessage() { return this.message; }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public void setMessage(String message) { this.message = message; }
}