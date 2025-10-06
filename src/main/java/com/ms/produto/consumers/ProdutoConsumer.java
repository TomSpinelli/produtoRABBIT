package com.ms.produto.consumers;

import com.ms.produto.dtos.ProdutoEstoqueDTO;
import com.ms.produto.models.Produto;
import com.ms.produto.services.ProdutoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConsumer {

    private final ProdutoService produtoService;

    public ProdutoConsumer(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @RabbitListener(queues = "${broker.queue.produto.name}")
    public void listenEmailQueue(@Payload ProdutoEstoqueDTO dto){
            var produto =  new Produto();
            BeanUtils.copyProperties(dto, produto);
            produtoService.verificarEstoque(dto.idProduto(), dto.quantidade());
            System.out.println("produto disponivel");
    }


}
