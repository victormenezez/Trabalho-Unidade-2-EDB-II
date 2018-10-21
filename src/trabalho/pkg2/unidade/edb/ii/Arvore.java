/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoedb2;
import static java.lang.Integer.max;
import static java.lang.Math.pow;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author victor
 */
public class Arvore {

    No raiz;
    int qnt_nos = 0;

    public Arvore() {
        raiz = null;
    }

    public Arvore(No n) {
        raiz = n;
    }

    //metodo para inserir um No na arvore
    public void inserirNo(No node, int dado) {
        if (node != null) {
            if (dado < node.dado) {
                if (node.esq != null) {
                    inserirNo(node.esq, dado);
                } else {
                    System.out.println("Inserindo " + dado + " a esquerda de " + node.dado);
                    node.esq = new No(dado);
                }
            } else if (dado > node.dado) {
                if (node.dir != null) {
                    inserirNo(node.dir, dado);
                } else {
                    System.out.println("Inserindo " + dado + " a direita de " + node.dado);
                    node.dir = new No(dado);
                }
            }
        } else {
            System.out.println("Criando árvore");
            raiz = new No(dado);
        }
        indiceNo();
    }

    //metodo que remove o No informado
    public No removeAtual(No atual) {
        No aux1, aux2;
        if (atual.esq == null) {
            aux2 = atual.dir;
            return aux2;
        }
        aux1 = atual;
        aux2 = atual.esq;
        while (aux2.dir != null) {
            aux1 = aux2;
            aux2 = aux2.dir;
        }
        if (aux1 != atual) {
            aux1.dir = aux2.esq;
            aux2.esq = atual.esq;
        }
        aux2.dir = atual.dir;

        return aux2;
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
                indiceNo();
                return 1;
            }
            anterior = atual;
            if (dado > atual.dado) {
                atual = atual.dir;
            } else {
                atual = atual.esq;
            }
        }
        indiceNo();
        return 0;
    }

    //metodo que imprime a arvore em ordem de forma iterativa
    public void emordem(No no) {
       if (no != null) {
           emordem(no.esq);
           System.out.print(no.dado + " ");
           emordem(no.dir);
       }
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

    //metodo que atribui um indice a cada No (em ordem)
    public void indiceNo() {
        qnt_nos = 0;
        Stack<No> stack = new Stack<>();
        No node = raiz;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.add(node);
                node = node.esq;
            } else {
                node = stack.pop();
                qnt_nos++;
                node.indice = qnt_nos;
                node = node.dir;
            }
        }
    }

    //metodo que recupera o elemento no indice x (em ordem)
    public Integer enesimoElemento(int n) {

        if(n > qnt_nos) return null;
        if(n == raiz.indice) return raiz.dado;
        Stack<No> stack = new Stack<>();
        stack.add(raiz);
        No no;
        while(!stack.isEmpty()){
            no = stack.pop();
            if(n == no.indice) return no.dado;
            if(no.esq != null && n < no.indice)
                stack.add(no.esq);
            else if(no.dir != null)
                stack.add(no.dir);
        }
        return null;
    }

    //metodo que retorna o indice do elemento x (em ordem)
    public Integer posicao(int x) {
        if (raiz == null) {
            System.out.println("Valor não encontrado");
            return null;
        }

        Stack<No> stack = new Stack<>();
        stack.add(raiz);
        int count = 1;

        while (!stack.empty()) {
            No no = stack.pop();
            if (no.dado == x) {
                return count;
            }

            if (no.dado > x) {
                stack.add(no.esq);
            } else {
                stack.add(no.dir);
            }
            count++;
        }

        return null;
    }

    //metodo que retorna a mediana dos elementos
    public Integer mediana() {
        if (raiz == null) {
            return null;
        }
        if (qnt_nos == 1) {
            return raiz.dado;
        }
        if (qnt_nos % 2 == 0) {
            return enesimoElemento(qnt_nos / 2);
        } else {
            return enesimoElemento((qnt_nos / 2) + 1);
        }
    }

    //metodo que informa se a ABB eh completa (2^(h-1) <= n <= 2^h -1)
    public boolean ehCompleta() {
        double aux1 = pow(2, (altura(raiz) - 1));
        double aux2 = pow(2, altura(raiz)) - 1;
        return aux1 <= qnt_nos && aux2 >= qnt_nos;
    }

    //metodo que informa se a ABB eh cheia (n ==  2^h -1)
    public boolean ehCheia() {
        double aux = pow(2, altura(raiz)) - 1;
        return aux == qnt_nos;
    }

    //metodo que retorna a altura da raiz da arvore
    public Integer altura(No no) {
        if (no == null) {
            return 0;
        }

        return max(altura(no.esq), altura(no.dir)) + 1;
    }

    //metodo que retorna uma string do percorrimento da ABB em nivel
    @Override
    public String toString() {
        if (raiz == null)
            throw new IllegalArgumentException("A árvore não pode ser nula!");

        LinkedList<No> fila = new LinkedList<>();
        fila.add(raiz);
        String s = "";

        while (!fila.isEmpty()) {
            No no = fila.remove();
            if (no.dir != null) {
                fila.add(no.dir);
                s += no.dado + " ";
            }
            if (no.esq != null) {
                fila.add(no.esq);
                s += no.dado + " ";
            }
        }
        return s;
    }
    
}

