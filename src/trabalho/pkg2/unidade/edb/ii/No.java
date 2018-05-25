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
public class No {
    int dado;
    int indice = 0;
    No esq, dir;

    public No(int dado){
        this.dado = dado;
        this.esq = null;
        this.dir = null;
    }
}
