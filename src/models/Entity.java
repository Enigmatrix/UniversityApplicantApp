package models;

import utils.Constants;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//the attribute below configures an id (UUID) generation for this class
//uuid is a unique id, that is randomly generated by the UUIDGenerator in the
//Jackson serialization library. It has no use in that the user never needs to
//care about it, but the Jackson library needs it to avoid circular references
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = Constants.Id)
public abstract class Entity {
	
}
