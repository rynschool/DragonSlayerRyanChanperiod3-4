public class Rooms {
    private int room;

    public Rooms(int room) {
        this.room = room;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int newRoom) {
        room = newRoom;
    }

    public void addRoom() {
        room++;
    }
}