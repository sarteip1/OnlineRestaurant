/*package tacocloud.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tacocloud.Data.Ingredient;
import tacocloud.Data.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;


    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }

    @Override
    public Taco save(Taco taco){
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(String ingredient : taco.getIngredients()){
            saveIngredienttoTaco(ingredient, tacoId);
        }
        return taco;
    }

    private void saveIngredienttoTaco(String ingredient, long tacoId) {
        jdbc.update("insert into TACO_INGREDIENTS (taco, ingredient) values (?,?)",
                tacoId, ingredient.getId()
        );
    }

    private long saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco (name,createdAt) values (?,?)",
                        Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())
                        )
                );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc,keyHolder);

        return keyHolder.getKey().longValue();
    }


}
*/

package tacocloud.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacocloud.Data.Ingredient;
import tacocloud.Data.Taco;

@Slf4j
@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    private SimpleJdbcInsert tacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.tacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco")
                .usingGeneratedKeyColumns("id");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (String ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
        values.put("createdAt", taco.getCreatedAt());
        long tacoId = tacoInserter.executeAndReturnKey(values).longValue();
        return tacoId;
    }

    private void saveIngredientToTaco(
            String ingredient, long tacoId) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) " +
                "values (?, ?)", tacoId, ingredient);
    }
/*
 // błędna wersja z książki
    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco (name, createdAt) values (?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }
*/
}