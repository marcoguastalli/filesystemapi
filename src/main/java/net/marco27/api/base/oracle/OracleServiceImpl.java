package net.marco27.api.base.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class OracleServiceImpl implements OracleService {

    @Autowired
    protected DataSource dataSource;
}
