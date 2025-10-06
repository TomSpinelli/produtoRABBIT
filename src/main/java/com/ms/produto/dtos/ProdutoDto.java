package com.ms.produto.dtos;

public record ProdutoDto(
        Long id,
        String nome,
        Long preco,
        Long quantidade
) {
}
