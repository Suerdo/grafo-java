import java.util.*;

public class Grafo {
    private int numeroDeVertices; 
    private LinkedList<Integer>[] listaDeAdjacencia; 

    
    Grafo(int numeroDeVertices) {
        this.numeroDeVertices = numeroDeVertices;
        listaDeAdjacencia = new LinkedList[numeroDeVertices];
        for (int i = 0; i < numeroDeVertices; ++i)
            listaDeAdjacencia[i] = new LinkedList();
    }

    // Adicionar uma aresta ao grafo
    void adicionarAresta(int verticeOrigem, int verticeDestino) {
        listaDeAdjacencia[verticeOrigem].add(verticeDestino);
    }

    // Busca em Largura
    void buscaEmLargura(int verticeInicial) {
        boolean visitado[] = new boolean[numeroDeVertices];

        LinkedList<Integer> fila = new LinkedList<Integer>();

        visitado[verticeInicial] = true;
        fila.add(verticeInicial);

        while (fila.size() != 0) {
            verticeInicial = fila.poll();
            System.out.print(verticeInicial + " ");

            Iterator<Integer> i = listaDeAdjacencia[verticeInicial].listIterator();
            while (i.hasNext()) {
                int vizinho = i.next();
                if (!visitado[vizinho]) {
                    visitado[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }
    }

    // Busca em Profundidade
    void buscaEmProfundidade(int verticeInicial) {
        boolean visitado[] = new boolean[numeroDeVertices];
        buscaEmProfundidadeUtil(verticeInicial, visitado);
    }

    void buscaEmProfundidadeUtil(int vertice, boolean visitado[]) {
        visitado[vertice] = true;
        System.out.print(vertice + " ");

        Iterator<Integer> i = listaDeAdjacencia[vertice].listIterator();
        while (i.hasNext()) {
            int vizinho = i.next();
            if (!visitado[vizinho])
                buscaEmProfundidadeUtil(vizinho, visitado);
        }
    }

    // Impressão do Caminho
    void imprimirCaminho(int verticeOrigem, int verticeDestino) {
        List<Integer> caminho = new ArrayList<>();
        boolean visitado[] = new boolean[numeroDeVertices];
        imprimirCaminhoUtil(verticeOrigem, verticeDestino, visitado, caminho);

        System.out.println("Caminho de " + verticeOrigem + " para " + verticeDestino + ": " + caminho);
    }

    boolean imprimirCaminhoUtil(int vertice, int verticeDestino, boolean visitado[], List<Integer> caminho) {
        visitado[vertice] = true;
        caminho.add(vertice);

        if (vertice == verticeDestino)
            return true;

        Iterator<Integer> i = listaDeAdjacencia[vertice].listIterator();
        while (i.hasNext()) {
            int vizinho = i.next();
            if (!visitado[vizinho] && imprimirCaminhoUtil(vizinho, verticeDestino, visitado, caminho))
                return true;
        }

        caminho.remove(caminho.size() - 1);
        return false;
    }

    // Ordenação Topológica
    void ordenacaoTopologica() {
        Stack<Integer> pilha = new Stack<>();
        boolean visitado[] = new boolean[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            if (!visitado[i]) {
                ordenacaoTopologicaUtil(i, visitado, pilha);
            }
        }

        System.out.println("Ordenação Topológica:");
        while (!pilha.isEmpty()) {
            System.out.print(pilha.pop() + " ");
        }
    }

    void ordenacaoTopologicaUtil(int vertice, boolean visitado[], Stack<Integer> pilha) {
        visitado[vertice] = true;

        Iterator<Integer> i = listaDeAdjacencia[vertice].listIterator();
        while (i.hasNext()) {
            int vizinho = i.next();
            if (!visitado[vizinho]) {
                ordenacaoTopologicaUtil(vizinho, visitado, pilha);
            }
        }

        pilha.push(vertice);
    }

    // Componentes Fortemente Conexas
    void componentesFortementeConexas() {
        Stack<Integer> pilha = new Stack<>();
        boolean visitado[] = new boolean[numeroDeVertices];

        for (int i = 0; i < numeroDeVertices; i++) {
            if (!visitado[i]) {
                componenteFortementeConexaUtil(i, visitado, pilha);
            }
        }
    }

    void componenteFortementeConexaUtil(int vertice, boolean visitado[], Stack<Integer> pilha) {
        visitado[vertice] = true;
        pilha.push(vertice);

        int minimo = vertice;
        Iterator<Integer> i = listaDeAdjacencia[vertice].listIterator();
        while (i.hasNext()) {
            int vizinho = i.next();
            if (!visitado[vizinho]) {
                componenteFortementeConexaUtil(vizinho, visitado, pilha);
            }
            minimo = Math.min(minimo, vizinho);
        }

        if (minimo == vertice) {
            while (pilha.peek() != vertice) {
                System.out.print(pilha.pop() + " ");
            }
            System.out.println(pilha.pop());
        }
    }
}

