package edu.csula.datascience.aquisition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;



public class CollectorTest {
	private Collector<SimpleModel, MockData> collect;
	private Source<MockData> deathSource;
	
	@Before
    public void setup() {
        collect = (Collector<SimpleModel, MockData>) new MockCollector();
        deathSource = (Source<MockData>) new MockSource();
    }

    @Test
    public void mungee() throws Exception {
        List<SimpleModel> list = (List<SimpleModel>) collect.mungee(deathSource.next());
        
        for(SimpleModel temp:list){
        	
        	System.out.println("list"+temp);
        }
        ArrayList<SimpleModel> lst = new ArrayList<SimpleModel>();
        List<MockData> expectedList = Lists.newArrayList(
        		new MockData(null, null, null, null, null, null),
				new MockData("2000", "63", "M", "new York", "cancer", "unmerried")
            );
        /*List<SimpleModel> expectedList = Lists.newArrayList(
                new SimpleModel("PA", "BUS LANE VIOLATION","5","JTW5438","PAS","1206P"),
                new SimpleModel("NY", "BUS LANE VIOLATION","16","GTH1661","PAS","0134P")
            );*/

        
        for (int i = 0; i < 2; i ++) {
            Assert.assertEquals(list.get(i).getAge(), lst.get(i).getAge());
            Assert.assertEquals(list.get(i).getCauseofdeath(), lst.get(i).getCauseofdeath());
        }
    }
}
