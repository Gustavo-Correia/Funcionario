import java.util.ArrayList;
import java.util.List;

public class Empregado {
    private String cpf;
    private String nome;
    private int idade;
    private char sexo; // 'M' ou 'F'
    private String cargo;
    private double salario;
    private List<Dependente> dependentes;


    // Construtor
     public Empregado(String cpf, String nome, int idade, char sexo, String cargo, double salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.cargo = cargo;
        this.salario = salario;
        this.dependentes = new ArrayList<>();
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public char getSexo() {
        return sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método para aumentar o salário em um percentual
    public void aumentarSalario(double percentualAumento) {
        salario += salario * (percentualAumento / 100.0);
    }


    //dependente



   

    // Getters e setters

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    // Método para adicionar um dependente ao empregado
    public void adicionarDependente(Dependente dependente) {
        dependentes.add(dependente);
    }

    // Método para remover um dependente pelo CPF
    public void removerDependentePorCpf(String cpf) {
        dependentes.removeIf(dependente -> dependente.getCpf().equals(cpf));
    }

    // Método para obter o total de dependentes
    public int getTotalDependentes() {
        return dependentes.size();
    }

    // Método para listar os dependentes do empregado
    public void listarDependentes() {
        System.out.println("Dependentes de " + nome + ":");
        for (Dependente dependente : dependentes) {
            System.out.println("CPF: " + dependente.getCpf() + ", Nome: " + dependente.getNome()
                    + ", Sexo: " + dependente.getSexo() + ", Idade: " + dependente.getIdade());
        }
    }
}



