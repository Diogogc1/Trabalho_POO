/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;

/**
 *
 * @author diogo
 */
public class AlimentoDAO {
    Alimento alimentos[] = new Alimento[20];
    private final Login login = new Login();

    public AlimentoDAO(Pessoa pessoa) {
        //GORDURA
        adicionar(new Alimento("Ovo", 1.2, 15.6, 18.6, 1, pessoa));
        adicionar(new Alimento("Castanha-de-caju torrada", 29.1, 18.5, 46.3, 1, pessoa));
        adicionar(new Alimento("Manteiga", 0.1, 0.4, 82.4, 1, pessoa));
        
        //CARBOIDRATO
        adicionar(new Alimento("Arroz", 28.1, 2.5, 0.2, 1, pessoa));
        adicionar(new Alimento("Pao frances", 49.9, 9.4, 3.7, 1, pessoa));
        adicionar(new Alimento("Feijao", 13.8, 4.8, 0.5, 1, pessoa));
        
        //PROTEINAS
        adicionar(new Alimento("Carne (coxão mole)", 0, 32.4, 8.9, 1, pessoa));
        adicionar(new Alimento("Frango Caipira (sem Pele)", 0, 29.6, 7.7, 1, pessoa));
        adicionar(new Alimento("Picanha assada (sem gordura)", 0, 21.3, 4.7, 1, pessoa));
    }
    
    //ADICIONAR - PERCORRE O VETOR E PROCURA UMA POSIÇÃO VAZIA PARA ADICIONAR
    public boolean adicionar(Alimento alimento){
        for (int i = 0; i < alimentos.length; i++) {
            if(alimentos[i] == null){
                alimentos[i] = alimento;
                return true;
            } 
        }
        return false;
    }
    
    //REMOVER - PERCORRE O VETOR E PROCURA O ALIMENTO PARA SER REMOVIDO
    public boolean remover(Alimento alimento){
        for (int i = 0; i < alimentos.length; i++) {
            if(alimentos[i].equals(alimento)){
                alimentos[i] = null;
                return true;
            }
        }
        return false;
    }
    
    //ALTERAR
    public boolean alterar(Alimento alimento, Alimento novoAlimento){
        for (Alimento alimento1 : alimentos) {
            if (alimento.equals(alimento1)) {
                alimento1.setNome(novoAlimento.getNome());
                alimento1.setPorcao(novoAlimento.getPorcao());
                alimento1.setCarboidratos(novoAlimento.getCarboidratos());
                alimento1.setProteinas(novoAlimento.getProteinas());
                alimento1.setGorduras(novoAlimento.getGorduras());
                alimento1.setCalorias(novoAlimento.getCalorias());
                alimento1.setDataDeModificacao(LocalDate.now());
                return true;
            }
        }
        return false;
    }
    
    //BUSCAR
    public Alimento buscar(long idAlimento){
        for (Alimento a : alimentos) {
            if (a != null && a.getId() == idAlimento) {
                return a;
            } 
        }
        return null;
    }
    
    //BUSCAR NOME
    public Alimento buscarNome(String nome){
        for (Alimento alimento : alimentos) {
            if (alimento != null && alimento.getNome().equals(nome)) {
                return alimento;
            } 
        }
        return null;
    }
     
    public boolean isVazio(){
        for (Alimento alimento : alimentos) {
            if(alimento != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== ALIMENTOS ==========");
        for (Alimento alimento : alimentos) {
            if(alimento != null && alimento.getPessoa().equals(login.getPessoaLogada())){
                sb.append("\n ID: ").append(alimento.getId()).
                append("\n Nome: ").append(alimento.getNome()).
                append("\n Porcao: ").append(alimento.getPorcao()).
                append("\n Carboidratos: ").append(alimento.getCarboidratos()).
                append("\n Proteinas: ").append(alimento.getProteinas()).
                append("\n Gorduras: ").append(alimento.getGorduras()).
                append("\n Calorias: ").append(alimento.getCalorias()).
                append("\n Data de Criacao: ").append(alimento.getDataCriacao()).
                append("\n Data de Modificacao: ").append(alimento.getDataModificacao()).
                append("\n ========================================");
            }
        }
        return sb.toString();
    }
}
