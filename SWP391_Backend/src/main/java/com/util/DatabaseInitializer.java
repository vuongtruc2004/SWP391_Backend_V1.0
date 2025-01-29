package com.util;

import com.entity.*;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.*;
import com.util.enums.*;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BlogRepository blogRepository;
    private final HashtagRepository hashtagRepository;

    @Autowired
    public DatabaseInitializer(RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BlogRepository blogRepository, HashtagRepository hashtagRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogRepository = blogRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Database initialization started!");

//        createPermission();
        createRole();
        createUser();
        createHashtags();
        createBlog();

        System.out.println("Database initialization completed!");
    }

    public void createPermission() {
        List<PermissionEntity> permissions = Arrays.asList(
                // auth controller
                new PermissionEntity("/auth/login/credentials", ApiMethodEnum.POST),
                new PermissionEntity("/auth/login/socials", ApiMethodEnum.POST),
                new PermissionEntity("/auth/refresh", ApiMethodEnum.GET),
                new PermissionEntity("/auth/logout", ApiMethodEnum.GET),

                // blog controller
                new PermissionEntity("/blogs", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/pinned", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/author", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/{id}", ApiMethodEnum.GET),
                new PermissionEntity("/blogs/hashtag", ApiMethodEnum.GET)

                // tiep tuc di em
        );
        permissionRepository.saveAll(permissions);
    }

    public void createRole() {
        if (!roleRepository.existsBy()) {
            List<RoleEntity> roles = List.of(
                    new RoleEntity(RoleNameEnum.ADMIN, permissionRepository.findAll()),
                    new RoleEntity(RoleNameEnum.USER, null)
            );
            roleRepository.saveAll(roles);
        }
    }

    public void createUser() {
        if (!userRepository.existsBy()) {
            RoleEntity admin = roleRepository.findByRoleName(RoleNameEnum.ADMIN).orElseThrow(() -> new RoleException("Role name not existed!"));
            List<UserEntity> users = List.of(
                    UserEntity.builder()
                            .username("truc")
                            .password(passwordEncoder.encode("123"))
                            .avatar("admin.jpg")
                            .fullname("Nguyen Vuong Truc")
                            .accountType(AccountTypeEnum.CREDENTIALS)
                            .email("trucnvhe180248@fpt.edu.vn")
                            .gender(GenderEnum.MALE)
                            .job(JobEnum.STUDENT)
                            .role(admin)
                            .build()
            );
            userRepository.saveAll(users);
        }
    }

    public void createHashtags() {
        if (!hashtagRepository.existsBy()) {
            List<HashtagEntity> hashtags = List.of(
                    HashtagEntity.builder().tagName("java").build(),
                    HashtagEntity.builder().tagName("javascript").build(),
                    HashtagEntity.builder().tagName("typescript").build(),
                    HashtagEntity.builder().tagName("html").build(),
                    HashtagEntity.builder().tagName("css").build(),
                    HashtagEntity.builder().tagName("machine learning").build(),
                    HashtagEntity.builder().tagName("reactjs").build(),
                    HashtagEntity.builder().tagName("nextjs").build(),
                    HashtagEntity.builder().tagName("c++").build(),
                    HashtagEntity.builder().tagName("python").build(),
                    HashtagEntity.builder().tagName("spring boot").build(),
                    HashtagEntity.builder().tagName("docker").build(),
                    HashtagEntity.builder().tagName("kubernetes").build(),
                    HashtagEntity.builder().tagName("ai").build(),
                    HashtagEntity.builder().tagName("deep learning").build(),
                    HashtagEntity.builder().tagName("flutter").build(),
                    HashtagEntity.builder().tagName("nodejs").build(),
                    HashtagEntity.builder().tagName("golang").build(),
                    HashtagEntity.builder().tagName("rust").build(),
                    HashtagEntity.builder().tagName("database").build(),
                    HashtagEntity.builder().tagName("mysql").build(),
                    HashtagEntity.builder().tagName("mongodb").build(),
                    HashtagEntity.builder().tagName("postgresql").build(),
                    HashtagEntity.builder().tagName("graphql").build(),
                    HashtagEntity.builder().tagName("restapi").build(),
                    HashtagEntity.builder().tagName("android").build(),
                    HashtagEntity.builder().tagName("ios").build(),
                    HashtagEntity.builder().tagName("aws").build(),
                    HashtagEntity.builder().tagName("cloud computing").build(),
                    HashtagEntity.builder().tagName("cybersecurity").build()
            );
            hashtagRepository.saveAll(hashtags);
        }
    }


    public Set<HashtagEntity> getRandomHashtags(Integer numberOfHashtags) {
        List<HashtagEntity> hashtags = hashtagRepository.findAll();
        if (hashtags.size() < numberOfHashtags) {
            throw new IllegalArgumentException("Không đủ hashtag để chọn!");
        }

        Set<HashtagEntity> result = new HashSet<>();
        Random random = new Random();

        while (result.size() < numberOfHashtags) {
            result.add(hashtags.get(random.nextInt(hashtags.size())));
        }
        return result;
    }

    public void createBlog() {
        if (!blogRepository.existsBy()) {
            UserEntity user = userRepository.findByUsername("truc").orElseThrow(() -> new UserException("User not existed!"));
            List<BlogEntity> blogs = List.of(
                    BlogEntity.builder()
                            .title("Lập Trình Web Từ A Đến Z: Hướng Dẫn Chi Tiết")
                            .content("<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>")
                            .plainContent(Jsoup.parse("<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("1.jpg")
                            .hashtags(this.getRandomHashtags(3))
                            .build(),
                    BlogEntity.builder()
                            .title("Những Sai Lầm Cần Tránh Khi Học Lập Trình")
                            .content("<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>")
                            .plainContent(Jsoup.parse("<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("2.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .build(),
                    BlogEntity.builder()
                            .title("Top 10 Công Cụ Hữu Ích Cho Lập Trình Viên")
                            .content("<p>Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. <strong>Dưới đây</strong> là danh sách 10 công cụ không thể thiếu.</p><ul><li>VS Code</li><li>Postman</li><li>Docker</li></ul>")
                            .plainContent(Jsoup.parse("<p>Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. <strong>Dưới đây</strong> là danh sách 10 công cụ không thể thiếu.</p><ul><li>VS Code</li><li>Postman</li><li>Docker</li></ul>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("3.jpg")
                            .hashtags(this.getRandomHashtags(4))
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Chủ Git Và GitHub: Hướng Dẫn Dành Cho Người Mới")
                            .content("<p><strong>Git và GitHub</strong> giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Git và GitHub</strong> giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("4.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Sao Để Trở Thành Lập Trình Viên Full-Stack?")
                            .content("<p><strong>Full-stack developer</strong> là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Full-stack developer</strong> là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("5.jpg")
                            .hashtags(this.getRandomHashtags(7))
                            .build(),
                    BlogEntity.builder()
                            .title("Cách Viết Code Sạch Và Dễ Bảo Trì")
                            .content("<p>Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như <strong>DRY</strong> và <em>KISS</em> để tối ưu mã nguồn.</p>")
                            .plainContent(Jsoup.parse("<p>Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như <strong>DRY</strong> và <em>KISS</em> để tối ưu mã nguồn.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("6.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .build(),
                    BlogEntity.builder()
                            .title("Học JavaScript Trong 30 Ngày: Lộ Trình Chi Tiết")
                            .content("<p>JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. <strong>Bài viết này</strong> sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.</p>")
                            .plainContent(Jsoup.parse("<p>JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. <strong>Bài viết này</strong> sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("7.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .build(),
                    BlogEntity.builder()
                            .title("Lập Trình Viên Có Cần Học Data Structures & Algorithms?")
                            .content("<p>Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. <strong>Bài viết này</strong> sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.</p>")
                            .plainContent(Jsoup.parse("<p>Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. <strong>Bài viết này</strong> sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("8.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .build(),
                    BlogEntity.builder()
                            .title("Những Ngôn Ngữ Lập Trình Phổ Biến Năm 2025")
                            .content("<p><strong>Python, JavaScript, Go</strong> và <em>Rust</em> là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Python, JavaScript, Go</strong> và <em>Rust</em> là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("9.jpg")
                            .hashtags(this.getRandomHashtags(9))
                            .build(),
                    BlogEntity.builder()
                            .title("Phát Triển Ứng Dụng Mobile: Nên Chọn React Native Hay Flutter?")
                            .content("<p>Chọn <strong>React Native</strong> nếu bạn thích JavaScript, hoặc <strong>Flutter</strong> nếu bạn muốn hiệu suất cao với Dart.</p>")
                            .plainContent(Jsoup.parse("<p>Chọn <strong>React Native</strong> nếu bạn thích JavaScript, hoặc <strong>Flutter</strong> nếu bạn muốn hiệu suất cao với Dart.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("10.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .build(),
                    BlogEntity.builder()
                            .title("Bí Quyết Học Lập Trình Hiệu Quả Cho Người Mới Bắt Đầu")
                            .content("<p>Học lập trình cần kiên nhẫn và thực hành liên tục. <strong>Bạn nên</strong> đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.</p>")
                            .plainContent(Jsoup.parse("<p>Học lập trình cần kiên nhẫn và thực hành liên tục. <strong>Bạn nên</strong> đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("11.jpg")
                            .hashtags(this.getRandomHashtags(5))
                            .build(),
                    BlogEntity.builder()
                            .title("Tìm Hiểu Về DevOps Và CI/CD")
                            .content("<p><strong>DevOps</strong> giúp cải thiện quy trình phát triển phần mềm, kết hợp <strong>CI/CD</strong> để tự động hóa triển khai.</p>")
                            .plainContent(Jsoup.parse("<p><strong>DevOps</strong> giúp cải thiện quy trình phát triển phần mềm, kết hợp <strong>CI/CD</strong> để tự động hóa triển khai.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("12.jpg")
                            .hashtags(this.getRandomHashtags(4))
                            .build(),
                    BlogEntity.builder()
                            .title("Các Kỹ Năng Cần Có Để Trở Thành Lập Trình Viên Thành Công")
                            .content("<p>Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.</p>")
                            .plainContent(Jsoup.parse("<p>Để thành công trong lĩnh vực lập trình, bạn cần kỹ năng giải quyết vấn đề, giao tiếp và học hỏi liên tục.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("13.jpg")
                            .hashtags(this.getRandomHashtags(6))
                            .build(),
                    BlogEntity.builder()
                            .title("Điều Quan Trọng Cần Biết Khi Làm Việc Với API")
                            .content("<p>Hiểu về <strong>RESTful API</strong> và <em>WebSocket</em> sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.</p>")
                            .plainContent(Jsoup.parse("<p>Hiểu về <strong>RESTful API</strong> và <em>WebSocket</em> sẽ giúp bạn thiết kế các dịch vụ web hiệu quả hơn.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("14.jpg")
                            .hashtags(this.getRandomHashtags(7))
                            .build(),
                    BlogEntity.builder()
                            .title("Phát Triển Phần Mềm Với Scrum: Lợi Ích Và Thực Hành")
                            .content("<p><strong>Scrum</strong> là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.</p>")
                            .plainContent(Jsoup.parse("<p><strong>Scrum</strong> là phương pháp Agile phổ biến để phát triển phần mềm, giúp cải thiện năng suất và đảm bảo chất lượng.</p>").text())
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("15.jpg")
                            .hashtags(this.getRandomHashtags(8))
                            .build()
            );
            blogRepository.saveAll(blogs);
        }
    }

}
