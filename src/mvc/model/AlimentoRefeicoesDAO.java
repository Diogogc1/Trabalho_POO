/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

/**
 *
 * @author User
 */
public class AlimentoRefeicoesDAO {
    AlimentoRefeicoes alimentoRefeicoes[] = new AlimentoRefeicoes[10];
    private final Login login = new Login();
        
    private double carboidrato;
    private double proteina;
    private double gordura;
    private double calorias;
    Preferencias preferenciaNova;
    
    //ADICIONAR - PERCORRE O VETOR E PROCURA UMA POSIÇÃO VAZIA PARA ADICIONAR
    public boolean adicionar(AlimentoRefeicoes alimentoRefeicao){
        for (int i = 0; i < alimentoRefeicoes.length; i++) {
            if(alimentoRefeicoes[i] == null){
                alimentoRefeicoes[i] = alimentoRefeicao;
                return true;
            } 
        }
        return false;
    }
    
    //REMOVER ALIMENTO
    public boolean removerAlimento(Alimento alimento){
        for (int i = 0; i < alimentoRefeicoes.length; i++) {
            if(alimentoRefeicoes[i] != null && alimentoRefeicoes[i].getAlimento().equals(alimento)){
                alimentoRefeicoes[i] = null;
                return true;
            }
        }
        return false;
    }
    
    //REMOVER REFEICAO
    public boolean removerRefeicao(Refeicao refeicao){
        for (int i = 0; i < alimentoRefeicoes.length; i++) {
            if(alimentoRefeicoes[i].getRefeicao().equals(refeicao)){
                alimentoRefeicoes[i] = null;
                return true;
            }
        }
        return false;
    }
    
    //ALTERAR
    public boolean alterar(AlimentoRefeicoes alimentoRefeicao, AlimentoRefeicoes alimentoRefeicaoNovo){
        for (int i = 0; i < alimentoRefeicoes.length; i++) {
            if(alimentoRefeicoes[i].equals(alimentoRefeicao)){
                alimentoRefeicoes[i] = alimentoRefeicaoNovo;
                return true;
            }
        }
        return false;
    }
    
    //BUSCAR REFEICAO
    public void mostrarAlimentosDeUmaRefeicao(Refeicao refeicaoEscolhida){
        for (AlimentoRefeicoes alimentoRefeicao : alimentoRefeicoes) {
            if(alimentoRefeicao != null && alimentoRefeicao.getRefeicao().equals(refeicaoEscolhida)){
                System.out.println(alimentoRefeicao);
            }
        }
    }
    
    
    public boolean bateuMetaRefeicao(Refeicao refeicaoNova){
        carboidrato = 0;
        proteina = 0;
        gordura = 0;
        calorias = 0;
        
        for (AlimentoRefeicoes alimentoRefeicao : alimentoRefeicoes) {
            if(alimentoRefeicao != null && alimentoRefeicao.getRefeicao().equals(refeicaoNova)){
                carboidrato += (alimentoRefeicao.getAlimento().getCarboidratos() * alimentoRefeicao.getAlimento().getPorcao()) / refeicaoNova.getCarboidrato();
                proteina += (alimentoRefeicao.getAlimento().getProteinas() * alimentoRefeicao.getAlimento().getPorcao()) / refeicaoNova.getProteina();
                gordura += (alimentoRefeicao.getAlimento().getGorduras() * alimentoRefeicao.getAlimento().getPorcao()) / refeicaoNova.getGordura();
                calorias += (alimentoRefeicao.getAlimento().getCalorias() * alimentoRefeicao.getAlimento().getPorcao()) / refeicaoNova.getCalorias();
            }
        }
  
        return carboidrato >= 1 && proteina >= 1 && gordura >= 1 && calorias >= 1;
    }
    
    public AlimentoRefeicoes cadastrarAutomaticoAlimentoRefeicoes(Refeicao refeicao, int j, PreferenciasDAO preferenciasDAO){
        preferenciaNova = preferenciasDAO.buscarNaoNulo(j);
        
        //GUARDANDO A PORCENTAGEM QUE O ALIMENTO REPRESENTA EM RELAÇÃO AO TOTAL DA REFEIÇÃO
        carboidrato = (preferenciaNova.getAlimento().getCarboidratos() * 100 * preferenciaNova.getAlimento().getPorcao()) / refeicao.getCarboidrato();
        proteina = (preferenciaNova.getAlimento().getProteinas() * 100 * preferenciaNova.getAlimento().getPorcao()) / refeicao.getProteina();
        gordura = (preferenciaNova.getAlimento().getGorduras() * 100 * preferenciaNova.getAlimento().getPorcao()) / refeicao.getGordura();
        calorias = (preferenciaNova.getAlimento().getCalorias() * 100 * preferenciaNova.getAlimento().getPorcao()) / refeicao.getCalorias();
        
        return new AlimentoRefeicoes(refeicao, preferenciaNova.getAlimento(), preferenciaNova.getAlimento().getPorcao(), carboidrato, proteina, gordura, calorias);
    }
    
    //TO STRING
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("====== PREFERENCIAS ======");
        for(AlimentoRefeicoes alimentoRefeicao : alimentoRefeicoes) {
            if(alimentoRefeicao != null && alimentoRefeicao.getAlimento().getPessoa().equals(login.getPessoaLogada())){
                sb.append("\n ID: ").append( alimentoRefeicao.getId()).
                append("\n Alimento: ").append( alimentoRefeicao.getAlimento().getNome()).
                append("\n Refeicao: ").append( alimentoRefeicao.getAlimento().getNome()).
                append("\n\n Data de Criacao: ").append( alimentoRefeicao.getDataCriacao()).
                append("\n Data de Modificacao: ").append( alimentoRefeicao.getDataModificacao()).
                append("\n ======================================== \n\n");
            }
        }
        return sb.toString();
    }
}
