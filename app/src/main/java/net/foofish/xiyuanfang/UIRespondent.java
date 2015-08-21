package net.foofish.xiyuanfang;

import net.foofish.xiyuanfang.models.Post;

import java.util.List;

/**
 * Created by lzjun on 6/26/15.
 */
public interface UIRespondent {
    void onInitializeDone( List<Post> data);
    void onLoadMoreDone(List<Post> data);
    void onRefreshDone(List<Post> data);

}
