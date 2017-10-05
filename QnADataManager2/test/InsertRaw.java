
import dataAccess.FileManager;
import dataModel.DataSet;

/**
 *
 * @author wesley
 */
public class InsertRaw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String rawPath = "./data/raw2.json";
        String datasetPath = "./data/dataset.json";

        //Load dataset
        DataSet dataset = dataAccess.FileManager.load(datasetPath);
        DataSet newdata = dataAccess.FileManager.loadRaw(rawPath);

        if (newdata != null) {
            if (dataset == null) {
                //New Dataset
                dataset = new DataSet(newdata.getTopics());
            } else {
                //Insert new data
                dataset.getTopics().addAll(newdata.getTopics());
            }
        }

        //Removing Duplicated
        dataset = FileManager.removeDuplicate(dataset);
        
        //Save dataset
        FileManager.save(dataset, datasetPath);
        
    }

}
