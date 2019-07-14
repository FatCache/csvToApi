package com.example.demo;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.CSVToDatabaseImp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVToDatabaebaseTest {

	@Value("${csv.FileName}")
	private String csvproperty;
	
	
    @Test
    public void csvFileExistInResource()  {
    	File f = new File(getClass().getClassLoader().getResource(csvproperty).getFile());
    	assertNotNull("CSV at Path is NULL.",f);
    	
    	
    }
}
