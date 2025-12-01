package com.iset.BackendEcommerce.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record PanierResponse(
        List<PanierItemResponse> items,
        BigDecimal total,
        int nombreArticles
) {}
