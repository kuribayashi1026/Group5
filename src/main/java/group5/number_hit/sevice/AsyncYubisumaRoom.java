package group5.number_hit.sevice;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import group5.number_hit.model.Room;
import group5.number_hit.model.RoomMapper;
import group5.number_hit.model.UserMapper;

@Service
public class AsyncYubisumaRoom {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncYubisumaRoom.class);

  @Autowired
  UserMapper uMapper;

  @Autowired
  RoomMapper rMapper;

  public ArrayList<Room> syncShowRoomList() {
    return rMapper.selectAll();
  }

  @Transactional
  public void syncJoinRoom(int id) {
    Room r = rMapper.selectRoomById(id);

    if (r == null) {
      rMapper.insertUser(rMapper.countAllUsers(), id, uMapper.selectNameById(id));
      this.dbUpdated = true;
    }
  }

  /**
   * dbUpdatedがtrueのときのみブラウザにDBからリストを取得して送付する
   *
   * @param emitter
   */
  @Async
  public void asyncShowRoomList(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true) {
        TimeUnit.MILLISECONDS.sleep(500);
        if (dbUpdated == false) {
          continue;
        }
        ArrayList<Room> room = this.syncShowRoomList();
        emitter.send(room);
        dbUpdated = false;
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowRoomList complete");
  }
}
