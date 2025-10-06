package com.ms.produto.services;

import com.ms.produto.models.Produto;
import com.ms.produto.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;

    }

    public Produto criarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto buscarProduto(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }


    public Produto verificarEstoque(Long idProduto, Long quantidade){
        return produtoRepository.findById(idProduto)
                .map(produto -> {
                    if (produto.getQuantidade() < quantidade) {
                        throw new IllegalArgumentException("Quantidade em estoque insuficiente!");
                    }
                    produto.setQuantidade(produto.getQuantidade() - quantidade);
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }




}
