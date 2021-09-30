package controleestoquemapa;
import java.util.ArrayList;

public class Produto {
    private String nome;
    private float precoUnit;
    private String unidade = "unidade";
    private int qtdEstoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(float precoUnit) {
        this.precoUnit = precoUnit;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    
    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    
    public Produto(){
        this("vazio",0,0);
    }

    public Produto(String nome, float precoUnit,int qtdEstoque) {
        this.nome = nome;
        this.precoUnit = precoUnit;
        this.qtdEstoque = qtdEstoque;
    }
    
    public void setAdicionarQuantidade(int qtd){
        this.qtdEstoque = this.qtdEstoque + qtd;
    }

    public void setDiminuirQuantidade(int qtd){
        this.qtdEstoque = this.qtdEstoque - qtd;
    }  
    
    @Override
    public String toString() {
        return "\nProduto:" + nome + "\nPreço Unitario = R$" + precoUnit + "\nUnidade = " + unidade +
                "\nQuantidade em Estoque = " + qtdEstoque + "\n";
    }
    
}
