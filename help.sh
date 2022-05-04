#Add Tag
git tag v1.1beta
git tag 1.0

#Add Tag to hash
git tag -f v1.1beta HEAD
git tag -f 1.0 HEAD

#Push Tag
git push origin v1.1beta
git push origin 1.0

#Push Tag with force
git push -f origin v1.1beta
git push -f origin 1.0
