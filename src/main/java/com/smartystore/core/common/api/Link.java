package com.smartystore.core.common.api;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class Link {
    private String rel;
    private String href;
}
