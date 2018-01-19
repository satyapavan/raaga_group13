package com.raaga.testcases;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.raaga.manager.BatchScheduleManager;
import com.raaga.to.TraineeTO;
import com.raaga.wrapper.RaagaWrapper;

public class TestCase5 {
	
   @Test
    public void getAllTraineeDetails() throws Exception{
		List<TraineeTO> t=new RaagaWrapper().getAllTrainee();
    
		Assert.assertTrue(t.size()!=0);
   

}
   
   @Test
	public void getBatchesForClosure() throws Exception{
		new BatchScheduleManager().getBatchesForClosure();
		Assert.assertTrue((new BatchScheduleManager().getBatchesForClosure())!=null);
		
	}
   @Test
	public void getBatchesForClosure1() throws Exception{
		new BatchScheduleManager().getBatchesForClosure();
		Assert.assertFalse((new BatchScheduleManager().getBatchesForClosure())==null);
	}
}
