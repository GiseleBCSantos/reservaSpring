package br.com.ifpi.catce.reservaspring.controller.page;

import jakarta.persistence.criteria.Order;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class PageWrapper<T> {

    private Page<T> page;
    private UriComponentsBuilder uriBuilder;

    public PageWrapper(Page<T> page, HttpServletRequest request) {
        this.page = page;
        this.uriBuilder = ServletUriComponentsBuilder.fromRequest(request);
    }

    public List<T> getConteudo(){
        return page.getContent();
    }

    public boolean isVazia(){
        return page.getContent().isEmpty();
    }

    public int getAtual(){
        return page.getNumber();
    }

    public boolean isPrimeira(){
        return page.isFirst();
    }

    public boolean isUltima(){
        return page.isLast();
    }

    public int getTotal(){
        return page.getTotalPages();
    }

    public String urlParaPagina(int pagina){
        return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
    }

    public String urlOrdenada(String propriedade){
        UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder.fromUriString(uriBuilder.build(true).encode().toUriString());

        String sortValue = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
        return uriBuilderOrder.replaceQueryParam("sort", sortValue).build(true).toUriString();
    }

    public String inverterDirecao(String propriedade){
        String direcao = "asc";

        Sort.Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
        if (order != null) {
            direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
        }

        return direcao;
    }

    public boolean descencente(String propriedade){
        return inverterDirecao(propriedade).equals("asc");
    }

    public boolean ordenada(String propriedade){
        Sort.Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
        if (order == null){
            return false;
        }

        return page.getSort().getOrderFor(propriedade) != null ? true : false;
    }
}
