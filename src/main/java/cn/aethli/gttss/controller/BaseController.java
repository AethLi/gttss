package cn.aethli.gttss.controller;

import cn.aethli.gttss.dao.BatchMapper;
import cn.aethli.gttss.domain.Batch;
import cn.aethli.gttss.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController(value = "/")
public class BaseController {

    @Autowired
    BatchMapper batchMapper;

    public SysUser sysUser = null;

    public SysUser getSysUser(Model model) {
        return (SysUser) model.asMap().get("currentUser");
    }

    public Batch getCurrentBatch() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Map<String,Object> queryMap=new HashMap();
        queryMap.put("date",new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        Batch batch = batchMapper.selectByDate(queryMap);
        return batch;
    }
}