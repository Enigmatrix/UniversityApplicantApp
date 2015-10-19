package repository;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.*;
import javafx.collections.ObservableList;

public class EntityRepository implements Repository {

	private DataRepresentation dataRepresentation = new DataRepresentation();
	private ObjectMapper mapper;
	private static String storageFilePath = "dataStore.json";
	private static File storageFile = new File(storageFilePath);

	public EntityRepository() {
		mapper = new ObjectMapper();
		// only recognize getter and setters, not fields
		mapper.setVisibility(PropertyAccessor.GETTER, Visibility.ANY)
				.setVisibility(PropertyAccessor.SETTER, Visibility.ANY)
				.setVisibility(PropertyAccessor.FIELD, Visibility.NONE);
	}

	private static Repository instance;

	// This is a bad singleton, but im too lazy to use DI and im not unit
	// testing anyway
	public static Repository getInstance() {
		if (instance == null) {
			instance = new EntityRepository();
			instance.loadDataRepresentation();
		}
		return instance;
	}

	@Override
	public void loadDataRepresentation() {
		try {
			dataRepresentation = mapper.readValue(storageFile,
					DataRepresentation.class);
		} catch (Exception e) {
			System.out
					.println("Error in Loading Data Representation. Stack Trace: ");
			e.printStackTrace();
		}
	}

	private Object syncLock = new Object();
	private ExecutorService execSvc = Executors.newCachedThreadPool();

	@Override
	public void saveCurrentDataRepresentation() {
		execSvc.execute(() -> {
			synchronized (syncLock) {
				try {
					mapper.writerWithDefaultPrettyPrinter().writeValue(
							storageFile, dataRepresentation);
				} catch (Exception e) {
					System.out
							.println("Error in Saving Data Representation. Stack Trace: ");
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public ObservableList<Applicant> getApplicants() {
		return dataRepresentation.getApplicants();
	}

	@Override
	public ObservableList<Course> getCourses() {
		return dataRepresentation.getCourses();
	}

	@Override
	public ObservableList<University> getUniversities() {
		return dataRepresentation.getUniversities();
	}

	@Override
	public ObservableList<UniversityRepresentative> getUniversityRepresentatives() {
		return dataRepresentation.getUniversityRepresentatives();
	}

	@Override
	public void setAppLocale(Locale appLocale) {
		dataRepresentation.setAppLocale(appLocale);
	}

	@Override
	public Locale getAppLocale() {
		return dataRepresentation.getAppLocale();
	}
}
