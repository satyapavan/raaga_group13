package com.raaga.entity;


	
	import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.raaga.bean.BatchTraineeDetailsPK;

	@Entity
	//@Table(name="Raaga_Batch_TraineeDetails")
	public class BatchTraineeEntity {
			
		    @EmbeddedId
			private BatchTraineeDetailsPK bPK;
			
			public BatchTraineeDetailsPK getBPK() {
				return bPK;
			}
			public void setBPK(BatchTraineeDetailsPK bpk) {
				bPK = bpk;
			}
			

			
			
		}

