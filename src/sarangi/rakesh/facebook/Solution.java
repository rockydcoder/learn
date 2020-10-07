package sarangi.rakesh.facebook;

import java.util.*;

public class Solution {

    public static void main(String[] args) throws InterruptedException {
        Facebook facebook = new Facebook();

        facebook.createPost(1,5);
        facebook.getNewsFeed(1);
        facebook.follow(1,2);
        facebook.createPost(2,6);
        facebook.getNewsFeed(1);
        facebook.createPost(1,7);
        facebook.getNewsFeed (1);
        facebook.getNewsFeed(2);
        facebook.deletePost(2,6);
        facebook.getNewsFeed(1);
        facebook.getNewsFeed(2);
        facebook.follow(2,1);
        facebook.getNewsFeed(2);
    }

}

class Facebook {

    Map<Integer, PriorityQueue<Post>> userFeed = new HashMap();
    Map<Integer, PriorityQueue<Post>> mergedUserFeed = new HashMap();
    Map<Integer, List<Integer>> userFollowers = new HashMap();

    public void createPost(Integer userId, Integer postId) throws InterruptedException {
        // Write your code here
        Thread.sleep(100);
        Post post = new Post(postId, userId);
        addToOwnFeed(userId, post);

        List<Integer> followers = userFollowers.get(userId);
        if (followers == null) {
            return;
        }

        for (int follower: followers) {
            addToMergedFeed(follower, post);
        }

    }

    public void deletePost(Integer userId, Integer postId) {
        // Write your code here
        PriorityQueue<Post> feed = userFeed.get(userId);
        if (feed != null) {
            Iterator<Post> it = feed.iterator();
            while (it.hasNext()) {
                Post currentPost = it.next();
                if (currentPost.id == postId) {
                    feed.remove(currentPost);
                    break;
                }
            }
        }

        feed = mergedUserFeed.get(userId);
        if (feed != null) {
            Iterator<Post> it = feed.iterator();
            while (it.hasNext()) {
                Post currentPost = it.next();
                if (currentPost.id == postId) {
                    feed.remove(currentPost);
                    break;
                }
            }
        }

        List<Integer> followers = userFollowers.get(userId);
        if (followers != null) {
            for (int follower: followers) {
                PriorityQueue<Post> followerFeed = mergedUserFeed.get(follower);
                if (followerFeed != null) {
                    Iterator<Post> it = followerFeed.iterator();
                    while (it.hasNext()) {
                        Post currentPost = it.next();
                        if (currentPost.id == postId) {
                            followerFeed.remove(currentPost);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void follow(Integer userId, Integer followeeId) {
        // Write your code here
        List<Integer> followers = userFollowers.get(followeeId);
        if (followers == null) {
            followers = new ArrayList();
        }
        followers.add(userId);
        userFollowers.putIfAbsent(followeeId, followers);
        PriorityQueue<Post> feed = mergedUserFeed.get(userId);
        if (feed == null) {
            feed = new PriorityQueue();
        }
        feed.addAll(userFeed.getOrDefault(followeeId, new PriorityQueue()));

    }

    public void unfollow(Integer userId, Integer followeeId) {
        // Write your code here
        List<Integer> followers = userFollowers.get(followeeId);
        if (followers == null) {
            return;
        }
        followers.remove(userId);
        PriorityQueue<Post> feed = mergedUserFeed.get(userId);
        if (feed == null) {
            return;
        }
        PriorityQueue<Post> followeeFeed = userFeed.get(followeeId);
        if (followeeFeed == null) {
            return;
        }
        Iterator<Post> it = followeeFeed.iterator();
        while (it.hasNext()) {
            Post post = it.next();
            feed.remove(post);
        }

    }

    public void getNewsFeed(Integer userId) {
        // Write your code here
        PriorityQueue<Post> feed = mergedUserFeed.getOrDefault(userId, new PriorityQueue<Post>());
        PriorityQueue<Post> cloneFeed = new PriorityQueue(feed);
        List<Integer> postIdsInFeed = new ArrayList();
        int counter = 0;
        while (counter < 10 && !cloneFeed.isEmpty()) {
            postIdsInFeed.add(cloneFeed.poll().id);
            counter++;
        }
        System.out.println(postIdsInFeed);
    }

    public void getNewsFeedPaginated(Integer userId, Integer pageNumber) {
        PriorityQueue<Post> feed = mergedUserFeed.getOrDefault(userId, new PriorityQueue<Post>());
        PriorityQueue<Post> cloneFeed = new PriorityQueue(feed);
        List<Integer> postIdsInFeed = new ArrayList();
        int counter = 0;
        while (counter < 10 && !cloneFeed.isEmpty()) {
            if (counter > pageNumber)
                postIdsInFeed.add(cloneFeed.poll().id);
            else {
                cloneFeed.poll();
            }
            counter++;
        }
        System.out.println(postIdsInFeed);

    }

    private void addToOwnFeed(Integer userId, Post post) {
        PriorityQueue<Post> feed = userFeed.get(userId);
        if (feed == null) {
            feed = new PriorityQueue<>();
        }
        feed.add(post);
        userFeed.put(userId, feed);

        feed = mergedUserFeed.get(userId);
        if (feed == null) {
            feed = new PriorityQueue<>();
        }
        feed.add(post);
        mergedUserFeed.put(userId, feed);
    }

    private void addToMergedFeed(Integer userId, Post post) {
        PriorityQueue<Post> feed = mergedUserFeed.get(userId);
        if (feed == null) {
            feed = new PriorityQueue();
        }
        feed.add(post);
        mergedUserFeed.putIfAbsent(userId, feed);
    }

}

class Post implements Comparable<Post> {
    Integer id;
    Integer authorId;
    Date created;

    public Post(Integer id, Integer authorId) {
        this.id = id;
        this.authorId = authorId;
        created = new Date();
    }

    @Override
    public int compareTo(Post post) {
        if (created.equals(post.created))
            return 0;
        else if (created.after(post.created))
            return -1;
        else
            return 1;
    }

}
