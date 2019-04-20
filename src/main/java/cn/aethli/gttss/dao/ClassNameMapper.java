package cn.aethli.gttss.dao;

import cn.aethli.gttss.domain.ClassName;
import org.springframework.stereotype.Component;

@Component
public interface ClassNameMapper {
    public ClassName selectById(ClassName s);

    public void insertWithAll(ClassName s);
}
