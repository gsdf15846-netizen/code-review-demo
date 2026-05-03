public class UserService {

    private UserRepository userRepository;

    public String getUserEmail(Long userId) {
        // BUG 1: 没判 userId == null
        // BUG 2: 没判 user == null,findById 返回 null 时下一行 NPE
        // BUG 3: 直接拼 SQL,SQL 注入风险 (虽然这里没真用)
        User user = userRepository.findById(userId);
        return user.getEmail().toLowerCase();
    }

    public void deleteUser(String userIdStr) {
        // BUG 4: parseLong 没 try-catch,非数字字符串会抛异常
        // BUG 5: 没做权限校验
        Long userId = Long.parseLong(userIdStr);
        userRepository.deleteById(userId);
    }

    public List<User> searchUsers(String keyword) {
        // BUG 6: 拼 SQL 字符串,典型 SQL 注入
        String sql = "SELECT * FROM users WHERE name LIKE '%" + keyword + "%'";
        return userRepository.executeRaw(sql);
    }
}
