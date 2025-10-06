package com.ms.produto.dtos;

public record ProdutoDto(
        Long id,
        String nome,
        String preco,
        Long quantidade
) {
}
