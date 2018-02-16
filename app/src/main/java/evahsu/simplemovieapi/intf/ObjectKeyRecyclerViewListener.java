package evahsu.simplemovieapi.intf;

/**
 * Created by evahsu on 2017/10/17.
 */

public interface ObjectKeyRecyclerViewListener {
    void onItemClick(int key, Object bean);
    boolean onItemLongClick(int key, Object bean);
}
