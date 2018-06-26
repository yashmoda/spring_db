package collegedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    private DataDAO dataDAO;

    @GetMapping(path = "/databyname")
    public ResponseEntity<Data> getDatabyName(@RequestParam String name)
    {
        Data data = dataDAO.getDatabyName(name);
        return new ResponseEntity<Data>(data, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Data>> getAllData()
    {
        List<Data> data = dataDAO.getAllData();
        return new ResponseEntity<List<Data>>(data, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Void> insertData(@RequestBody Data data)
    {
        dataDAO.insert(data);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
