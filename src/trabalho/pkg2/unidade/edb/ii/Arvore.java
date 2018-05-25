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
public class Arvore {

    No raiz;
    int qnt_elementos = 1;

    public Arvore() {
        raiz = null;
    }

    public Arvore(No n) {
        raiz = n;
    }

    //metodo para inserir um No na arvore
    public void inserirNo(No node, int dado) {
        //verifica se a árvore já foi criada
        if (node != null) {
            //Verifica se o dado a ser inserido é menor que o nodo corrente da árovre, se sim vai para subarvore esquerda
            if (dado < node.dado) {
                //Se tiver elemento no nodo esquerdo continua a busca
                if (node.esq != null) {
                    inserirNo(node.esq, dado);
                } else {
                    //Se nodo esquerdo vazio insere o novo nodo aqui
                    System.out.println("  Inserindo " + dado + " a esquerda de " + node.dado);
                    node.esq = new No(dado);
                }
                //Verifica se o dado a ser inserido é maior que o nodo corrente da árvore, se sim vai para subarvore direita
            } else if (dado > node.dado) {
                //Se tiver elemento no nodo direito continua a busca
                if (node.dir != null) {
                    inserirNo(node.dir, dado);
                } else {
                    //Se nodo direito vazio insere o novo nodo aqui
                    System.out.println("  Inserindo " + dado + " a direita de " + node.dado);
                    node.dir = new No(dado);
                }
            }
        } else {
            System.out.println("Criando árvore");
            raiz = new No(dado);
        }
    }

    //metodo que remove o No informado
    public No removeAtual(No atual) {
        No no1, no2;
        if (atual.esq == null) {
            no2 = atual.dir;
            return no2;
        }
        no1 = atual;
        no2 = atual.esq;
        while (no2.dir != null) {
            no1 = no2;
            no2 = no2.dir;
        }
        if (no1 != atual) {
            no1.dir = no2.esq;
            no2.esq = atual.esq;
        }
        no2.dir = atual.dir;

        return no2;
    }

    //metodo para remover um No na arvore
    public int removerNo(int dado) {
        if (raiz == null) {
            System.out.println("Erro: Árvore nula!");
            return 0;
        }
        No anterior = null;
        No atual = raiz;
        while (atual != null) {
            if (dado == atual.dado) {
                if (atual == raiz) {
                    raiz = removeAtual(atual);
                } else {
                    if (anterior.dir == atual) {
                        anterior.dir = removeAtual(atual);
                    } else {
                        anterior.esq = removeAtual(atual);
                    }
                }
                return 1;
            }
            anterior = atual;
            if (dado > atual.dado) {
                atual = atual.dir;
            } else {
                atual = atual.esq;
            }
        }
        return 0;
    }

    //metodo que imprime a arvore em ordem
    public void emordem(No no) {
        if (no != null) {
            emordem(no.esq);
            System.out.print(no.dado + " ");
            emordem(no.dir);
        }
    }

    public void tempo(No no) {
        if (no != null) {
            tempo(no.esq);
            System.out.print(no.indice + " ");
            tempo(no.dir);
        }
    }

    //metodo que atribui indice a cada No (em ordem)
    public void indiceEmOrdem(No no) {
        

//        int indice = no.indice;         
//        if (no.esq != null) {
//            no.esq.indice = no.indice;
//            no.indice = no.indice + 1;
//            indiceEmOrdem(no.esq);
//            no.indice = no.indice + 1;
//        }
//        if (no.dir != null) {
//            no.dir.indice = no.indice + 1;
//            indiceEmOrdem(no.dir);
//        }
    }

    //metodo para busca de No na arvore
    public No busca(int dado) {
        if (raiz == null) {
            System.out.println("Valor não encontrado");
            return null;
        }

        if (raiz.dado == dado) {
            return raiz;
        }

        if (raiz.dado > dado) {
            Arvore aux = new Arvore(raiz.esq);
            return aux.busca(dado);
        } else {
            Arvore aux = new Arvore(raiz.dir);
            return aux.busca(dado);
        }
    }
}
