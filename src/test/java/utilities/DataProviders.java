package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;

public class DataProviders {//is provided in utilities for reusability

    @DataProvider(name = "LoginData")
    public Object [][] getData() throws IOException {
        String path= ".\\testData\\OpenCart_LoginData.xlsx";

        ExcelUtility xlUtility = new ExcelUtility(path);
        int totalrows =xlUtility.getRowCount("Sheet1");
        int totalcols=xlUtility.getCellCount("Sheet1",1);

        String loginData[][]=new String[totalrows][totalcols];
        for(int i=1; i<=totalrows; i++){
            for(int j=0; j<totalcols; j++){
                loginData[i-1][j] = xlUtility.getCellData("Sheet1", i,j);
                //Reading row data from 1. so -1 is for the
            }
        }
        return loginData;
    }
}
