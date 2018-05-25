/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.pkg2.unidade.edb.ii;

/**
 *
 * @author victor
 */
public class TesteArvore {
    public static void main(String args[]){
        
        Arvore arvore = new Arvore();
        
        arvore.inserirNo(arvore.raiz, 8);
        arvore.inserirNo(arvore.raiz, 3);
        arvore.inserirNo(arvore.raiz, 10);
        arvore.inserirNo(arvore.raiz, 1);
        arvore.inserirNo(arvore.raiz, 6);
        arvore.inserirNo(arvore.raiz, 14);
        arvore.inserirNo(arvore.raiz, 4);
        arvore.inserirNo(arvore.raiz, 7);
        arvore.inserirNo(arvore.raiz, 13);
        
        System.out.println("");
        
        arvore.emordem(arvore.raiz);
        
        System.out.println("");
        
//        if(arvore.removerNo(3) == 1)
//            System.out.println("OK, removeu o 3");
//        else
//            System.out.println("deu ruim");
//      
        arvore.indiceEmOrdem(arvore.raiz);
        
//        arvore.emordem(arvore.raiz);
        
//        System.out.println(arvore.qnt_elementos);
        
        arvore.tempo(arvore.raiz);
//        
//        try{
//            System.out.println("sdvsdv" + n.dir.dado);
//        } catch (Exception e){
//            System.out.println("Erro: " + e);
//        }
    }
}
