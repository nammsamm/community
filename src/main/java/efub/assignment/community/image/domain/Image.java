package efub.assignment.community.image.domain;


import efub.assignment.community.global.BaseTimeEntity;
import efub.assignment.community.post.domain.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(nullable = false)
    private String uniqueName;

    @Column(nullable = false)
    private String originName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    private final static String supportedExtension[]={"jpg","jpeg","gif","bmp","png"};

    public Image(String originName){
        this.originName=originName;
        this.uniqueName=generateUniqueName(extractExtension(originName));
    }

    public void initPost(final Post post){
        if(this.post==null){
            this.post=post;
        }
    }

    private String generateUniqueName(String extension){
        return UUID.randomUUID().toString()+"."+extension;
    }

    private String extractExtension(String originName){
        try {
            String ext=originName.substring(originName.lastIndexOf(".")+1);
            if(isSupportedFormat(ext)) return ext;
        }catch (StringIndexOutOfBoundsException e){}
        throw new RuntimeException();
    }

    private boolean isSupportedFormat(String ext){
        return Arrays.stream(supportedExtension).anyMatch(e->e.equalsIgnoreCase(ext));
    }
}
