package com.util;

import com.entity.BlogEntity;
import com.entity.PermissionEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.RoleException;
import com.exception.custom.UserException;
import com.repository.BlogRepository;
import com.repository.PermissionRepository;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.util.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BlogRepository blogRepository;

    @Autowired
    public DatabaseInitializer(RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, BlogRepository blogRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogRepository = blogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Database initialization started!");

//        createPermission();
        createRole();
        createUser();
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
                new PermissionEntity("/blogs/pinned", ApiMethodEnum.GET)

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

    public void createBlog() {
        if (!blogRepository.existsBy()) {
            UserEntity user = userRepository.findByUsername("truc").orElseThrow(() -> new UserException("User not existed!"));
            List<BlogEntity> blogs = List.of(
                    BlogEntity.builder()
                            .title("Lập Trình Web Từ A Đến Z: Hướng Dẫn Chi Tiết")
                            .content("<p>Bạn muốn học lập trình web nhưng không biết bắt đầu từ đâu? <strong>Bài viết này</strong> sẽ giúp bạn hiểu rõ các khái niệm quan trọng từ frontend đến backend.</p><p>Bạn sẽ học <em>HTML, CSS, JavaScript, React</em> và <strong>Spring Boot</strong> để xây dựng website chuyên nghiệp.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("1.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Những Sai Lầm Cần Tránh Khi Học Lập Trình")
                            .content("<p>Khi mới học lập trình, nhiều người mắc phải những sai lầm phổ biến. <strong>Bài viết này</strong> sẽ chỉ ra những lỗi đó và cách khắc phục.</p><p>Tránh học lan man, tập trung vào thực hành và tham gia dự án thực tế để tiến bộ nhanh hơn.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("2.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Top 10 Công Cụ Hữu Ích Cho Lập Trình Viên")
                            .content("<p>Công cụ phù hợp giúp lập trình viên làm việc nhanh chóng và hiệu quả hơn. <strong>Dưới đây</strong> là danh sách 10 công cụ không thể thiếu.</p><ul><li>VS Code</li><li>Postman</li><li>Docker</li></ul>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("3.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Chủ Git Và GitHub: Hướng Dẫn Dành Cho Người Mới")
                            .content("<p><strong>Git và GitHub</strong> giúp lập trình viên quản lý mã nguồn và làm việc nhóm hiệu quả. Bài viết này sẽ hướng dẫn bạn từ cơ bản đến nâng cao.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("4.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Sao Để Trở Thành Lập Trình Viên Full-Stack?")
                            .content("<p><strong>Full-stack developer</strong> là một trong những vị trí hot nhất. Bài viết này giúp bạn nắm vững kỹ năng cần thiết và lộ trình học tập hiệu quả.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("5.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Cách Viết Code Sạch Và Dễ Bảo Trì")
                            .content("<p>Viết code không chỉ để máy tính hiểu, mà còn giúp đồng đội dễ đọc. Hãy áp dụng các nguyên tắc như <strong>DRY</strong> và <em>KISS</em> để tối ưu mã nguồn.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("6.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Học JavaScript Trong 30 Ngày: Lộ Trình Chi Tiết")
                            .content("<p>JavaScript là ngôn ngữ lập trình phổ biến nhất hiện nay. <strong>Bài viết này</strong> sẽ cung cấp cho bạn lộ trình học từ cơ bản đến nâng cao.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("7.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Lập Trình Viên Có Cần Học Data Structures & Algorithms?")
                            .content("<p>Cấu trúc dữ liệu và thuật toán là một phần quan trọng trong lập trình. <strong>Bài viết này</strong> sẽ giúp bạn hiểu cách áp dụng chúng vào thực tế.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("8.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Những Ngôn Ngữ Lập Trình Phổ Biến Năm 2025")
                            .content("<p><strong>Python, JavaScript, Go</strong> và <em>Rust</em> là những ngôn ngữ có xu hướng phát triển mạnh trong năm tới.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("9.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Phát Triển Ứng Dụng Mobile: Nên Chọn React Native Hay Flutter?")
                            .content("<p>Chọn <strong>React Native</strong> nếu bạn thích JavaScript, hoặc <strong>Flutter</strong> nếu bạn muốn hiệu suất cao với Dart.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("10.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Bí Quyết Học Lập Trình Hiệu Quả Cho Người Mới Bắt Đầu")
                            .content("<p>Học lập trình cần kiên nhẫn và thực hành liên tục. <strong>Bạn nên</strong> đặt mục tiêu rõ ràng, làm bài tập thực tế và tham gia dự án nhóm.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("11.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Tìm Hiểu Về DevOps Và CI/CD")
                            .content("<p><strong>DevOps</strong> giúp cải thiện quy trình phát triển phần mềm, kết hợp <strong>CI/CD</strong> để tự động hóa triển khai.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("12.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Làm Sao Để Tăng Lương Trong Ngành IT?")
                            .content("<p>Bạn muốn <strong>tăng lương</strong>? Hãy nâng cao kỹ năng, tham gia dự án lớn và đàm phán hợp đồng tốt hơn.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("13.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Hướng Dẫn Xây Dựng API RESTful Với Spring Boot")
                            .content("<p>API RESTful là một phần quan trọng của phát triển backend. <strong>Bài viết này</strong> sẽ hướng dẫn bạn cách tạo API với Spring Boot.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("14.jpg")
                            .build(),
                    BlogEntity.builder()
                            .title("Sự Khác Biệt Giữa SQL Và NoSQL")
                            .content("<p><strong>SQL</strong> phù hợp với dữ liệu có cấu trúc, còn <strong>NoSQL</strong> tốt hơn cho dữ liệu linh hoạt.</p>")
                            .user(user)
                            .createdAt(Instant.now())
                            .thumbnail("15.jpg")
                            .build()
            );
            blogRepository.saveAll(blogs);
        }
    }
}
