import java.util.Scanner;


public class Empresa {
    private Empregado[] empregados;
    private int totalEmpregados;

    public Empresa() {
        empregados = new Empregado[1000];
        totalEmpregados = 0;
    }

    public void cadastrarEmpregado(Empregado empregado) {
        if (totalEmpregados < 1000) {
            empregados[totalEmpregados] = empregado;
            totalEmpregados++;
            System.out.println("Empregado cadastrado com sucesso!");
        } else {
            System.out.println("Limite máximo de empregados atingido.");
        }
    }
    //sobrecarguinha para cadastrar mais um escravo da sociedade
    public void cadastrarEmpregado(String cpf, String nome, int idade, char sexo, String cargo, double salario) {
        Empregado empregado = new Empregado(cpf, nome, idade, sexo, cargo, salario);
        if (totalEmpregados < empregados.length) {
            empregados[totalEmpregados] = empregado;
            totalEmpregados++;
            System.out.println("Empregado cadastrado com sucesso!");
        } else {
            System.out.println("Limite máximo de empregados atingido.");
        }
    }
    public Empregado consultarEmpregadoPorCpf(String cpf) {
        for (int i = 0; i < totalEmpregados; i++) {
            if (empregados[i].getCpf().equals(cpf)) {
                return empregados[i];
            }
        }
        return null; // Retorna null se o empregado não for encontrado
    }

    public void listarEmpregados() {
        System.out.println("Lista de empregados:");
        for (Empregado empregado : empregados) {
            System.out.println("CPF: " + empregado.getCpf() + ", Nome: " + empregado.getNome());
        }
    }

    public void listarEmpregadosPorGenero(char genero) {
        System.out.println("Lista de empregados do gênero " + genero + ":");
        for (Empregado empregado : empregados) {
            if (empregado.getSexo() == genero) {
                System.out.println("CPF: " + empregado.getCpf() + ", Nome: " + empregado.getNome());
            }
        }
    }

    public void excluirEmpregado(String cpf) {
        int posicao = -1;
        // pegar a posiÇao do empregado
        for (int i = 0; i < totalEmpregados; i++) {
            if (empregados[i].getCpf().equals(cpf)) {
                posicao = i;
                break;
            }
        }
        //excluir a posicao
        if (posicao != -1) {
            for (int i = posicao; i < totalEmpregados - 1; i++) {
                empregados[i] = empregados[i + 1];
            }
            empregados[totalEmpregados - 1] = null;
            totalEmpregados--;
            System.out.println("Empregado excluído com sucesso!");
        } else {
            System.out.println("Empregado não encontrado.");
        }
    }

    public void alterarNomeEmpregado(String cpf, String novoNome) {
        for (int i = 0; i < totalEmpregados; i++) {
            if (empregados[i].getCpf().equals(cpf)) {
                empregados[i].setNome(novoNome);
                System.out.println("Nome do empregado alterado com sucesso!");
                return;
            }
        }
        System.out.println("Empregado não encontrado.");
    }

    public void aumentarSalariosPorGenero(char genero, double percentualAumento) {
        for (int i = 0; i < totalEmpregados; i++) {
            if (empregados[i].getSexo() == genero) {
                empregados[i].aumentarSalario(percentualAumento);
            }
        }
        System.out.println("Salários dos empregados do gênero " + genero + " aumentados em " + percentualAumento + "%.");
    }
    
    public void cadastrarDependente(String cpfEmpregado, String cpfDependente, String nomeDependente, char sexo, int idade) {
        Empregado empregado = consultarEmpregadoPorCpf(cpfEmpregado);
        if (empregado != null) {
            Dependente dependente = new Dependente(cpfDependente, nomeDependente, sexo, idade);
            empregado.adicionarDependente(dependente);
            System.out.println("Dependente cadastrado com sucesso para o empregado " + empregado.getNome());
        } else {
            System.out.println("Empregado não encontrado.");
        }
    }

    public int totalDependentes(String cpfEmpregado) {
        Empregado empregado = consultarEmpregadoPorCpf(cpfEmpregado);
        if (empregado != null) {
            return empregado.getTotalDependentes();
        } else {
            System.out.println("Empregado não encontrado.");
            return 0;
        }
    }
    
    public void excluirDependente(String cpfEmpregado, String cpfDependente) {
        Empregado empregado = consultarEmpregadoPorCpf(cpfEmpregado);
        if (empregado != null) {
            empregado.removerDependentePorCpf(cpfDependente);
            System.out.println("Dependente excluído com sucesso para o empregado " + empregado.getNome());
        } else {
            System.out.println("Empregado não encontrado.");
        }
    }

    public void listarFuncionariosComDependentes() {
        System.out.println("Lista de funcionários e seus dependentes:");
        for (int i = 0; i < totalEmpregados; i++) {
            Empregado empregado = empregados[i];
            System.out.println("Funcionário: " + empregado.getNome());
            empregado.listarDependentes();
            System.out.println("---------------");
        }
    }

    public void imprimirMenu() {
        System.out.println("1 - Cadastrar empregado");
        System.out.println("2 - Consultar dados de um empregado pelo cpf");
        System.out.println("3 - Aumentar o salário do empregado");
        System.out.println("4 - Listar Empregados");
        System.out.println("5 - Listar Empregados por genero");
        System.out.println("6 – Excluir empregado pelo cpf");
        System.out.println("7 – Alterar o nome do empregado pelo cpf");
        System.out.println("8 – Aumentar os salários dos empregados pelo genero");
        System.out.println("(9 - Cadastrar Dependente");
        System.out.println("10 – Excluir Dependente");
        System.out.println("11 – Total de dependentes");
        System.out.println("12 – Listar todos os funcionários e seus respectivos dependentes");
        System.out.println("13 - Sair do sistema");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Empresa empresa = new Empresa();

        int opcao = 0;
        while (opcao != 4) {
            empresa.imprimirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.println("Digite o CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Digite a idade: ");
                    int idade = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer
                    System.out.println("Digite o sexo (M/F): ");
                    char sexo = scanner.nextLine().charAt(0);
                    System.out.println("Digite o cargo: ");
                    String cargo = scanner.nextLine();
                    System.out.println("Digite o salário: ");
                    double salario = scanner.nextDouble();
                    Empregado empregado = new Empregado(cpf, nome, idade, sexo, cargo, salario);
                    empresa.cadastrarEmpregado(empregado);
                    break;
                case 2:
                    System.out.println("Digite o CPF do empregado a ser consultado: ");
                    String cpfConsulta = scanner.nextLine();
                    Empregado empregadoConsultado = empresa.consultarEmpregadoPorCpf(cpfConsulta);
                    if (empregadoConsultado != null) {
                        System.out.println("Nome: " + empregadoConsultado.getNome());
                        System.out.println("Idade: " + empregadoConsultado.getIdade());
                        System.out.println("Sexo: " + empregadoConsultado.getSexo());
                        System.out.println("Cargo: " + empregadoConsultado.getCargo());
                        System.out.println("Salário: " + empregadoConsultado.getSalario());
                    } else {
                        System.out.println("Empregado não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Digite o CPF do empregado para aumentar o salário: ");
                    String cpfAumento = scanner.nextLine();
                    Empregado empregadoAumento = empresa.consultarEmpregadoPorCpf(cpfAumento);
                    if (empregadoAumento != null) {
                        System.out.println("Digite o percentual de aumento: ");
                        double percentual = scanner.nextDouble();
                        empregadoAumento.aumentarSalario(percentual);
                        System.out.println("Salário atualizado com sucesso.");
                        System.out.println("Salário atual: " + empregadoAumento.getSalario());
                    } else {
                        System.out.println("Empregado não encontrado.");
                    }
                    break;
                case 4:
                    empresa.listarEmpregados();
                    break;
                case 5:
                    System.out.println("Digite o gênero (M/F) para listar os empregados:");
                    char generoListar = scanner.nextLine().charAt(0);
                    empresa.listarEmpregadosPorGenero(generoListar);
                    break;
                case 6:
                    System.out.println("Digite o CPF do empregado a ser excluído: ");
                    String cpfExcluir = scanner.nextLine();
                    empresa.excluirEmpregado(cpfExcluir);
                    break;
                case 7:
                    System.out.println("Digite o CPF do empregado a ter o nome alterado: ");
                    String cpfAlterarNome = scanner.nextLine();
                    System.out.println("Digite o novo nome: ");
                    String novoNome = scanner.nextLine();
                    empresa.alterarNomeEmpregado(cpfAlterarNome, novoNome);
                    break;
                case 8:
                    System.out.println("Digite o gênero (M/F) dos empregados a terem o salário aumentado: ");
                    char generoAumento = scanner.nextLine().charAt(0);
                    System.out.println("Digite o percentual de aumento: ");
                    double percentualAumento = scanner.nextDouble();
                    scanner.nextLine(); // Limpa o buffer
                    empresa.aumentarSalariosPorGenero(generoAumento, percentualAumento);
                    break;
                case 9:
                    System.out.println("Digite o CPF do empregado para cadastrar o dependente: ");
                    String cpfEmpregado = scanner.nextLine();
                    System.out.println("Digite o CPF do dependente: ");
                    String cpfDependente = scanner.nextLine();
                    System.out.println("Digite o nome do dependente: ");
                    String nomeDependente = scanner.nextLine();
                    System.out.println("Digite o sexo do dependente (M/F): ");
                    char sexoDependente = scanner.nextLine().charAt(0);
                    System.out.println("Digite a idade do dependente: ");
                    int idadeDependente = scanner.nextInt();
                    scanner.nextLine();

                    empresa.cadastrarDependente(cpfEmpregado, cpfDependente, nomeDependente, sexoDependente, idadeDependente);
                    case 10:
                   
                    System.out.println("Digite o CPF do empregado do qual deseja excluir o dependente: ");
                    String cpfEmpregadoExcluir = scanner.nextLine();
                    System.out.println("Digite o CPF do dependente a ser excluído: ");
                    String cpfDependenteExcluir = scanner.nextLine();
                    empresa.excluirDependente(cpfEmpregadoExcluir, cpfDependenteExcluir);
                    break;
                    case 11:
                
                    System.out.println("Digite o CPF do empregado para consultar o total de dependentes: ");
                    String cpfEmpregadoTotal = scanner.nextLine();
                    int totalDependentes = empresa.totalDependentes(cpfEmpregadoTotal);
                    System.out.println("Total de dependentes: " + totalDependentes);
                    break;
                    case 12:
                    empresa.listarFuncionariosComDependentes();
                    break;
                    case 13:
                    System.out.println("Saindo do sistema");
                    break;
                    default:
                    System.out.println("Opção inválida.");
                    break;
                    
            }
        }
        scanner.close();
    }
}