public class Main {
    public static void main(String args[]) {
        Grafo grafo = new Grafo(6);

        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 3);
        grafo.adicionarAresta(2, 4);
        grafo.adicionarAresta(2, 5);
        grafo.adicionarAresta(3, 1);
        grafo.adicionarAresta(4, 5);
        grafo.adicionarAresta(5, 2);

        System.out.println("Busca em Largura (começando do vértice 0):");
        grafo.buscaEmLargura(0);

        System.out.println("\n\nBusca em Profundidade (começando do vértice 0):");
        grafo.buscaEmProfundidade(0);

        System.out.println("\n\nImprimir Caminho de 0 para 3:");
        grafo.imprimirCaminho(0, 3);

        System.out.println("\n\nOrdenação Topológica:");
        grafo.ordenacaoTopologica();

        System.out.println("\n\nComponentes");
    }
}
