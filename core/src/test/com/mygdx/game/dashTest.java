package test.com.mygdx.Dash;

import com.mygdx.game.Player;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.assertTrue;

/** 
* Game Tester.
* 
* @author <Authors name> 
* @since <pre>mar 29, 2017</pre> 
* @version 1.0 
*/ 
public class dashTest {

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: create() 
* 
*/ 
@Test
public void testCreate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: render() 
* 
*/ 
@Test
public void testRender() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: dispose() 
* 
*/ 
@Test
public void testDispose() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: hej() 
* 
*/ 
@Test
public void testHej() throws Exception { 
//TODO: Test goes here... 
}

    @Test
    public void testHealth() {
        Player player = new Player();

        assertTrue(player.getHealth() == 3);
    }

}
