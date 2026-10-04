Typical workflow:

```bash
git init

git remote add origin git@github.com:YOUR_USERNAME/my-project.git

git clone git@github.com:YOUR_USERNAME/my-project.git my-project-copy

git switch -c feature-name      # Create branch

# ... make changes ...
git add .                       # Stage changes

git commit -m "Description"     # Commit

git push                        # Push to GitHub

# Create PR on GitHub, merge, then:
git switch master
git pull
```

When things go wrong:

```bash
# Discard changes to one file
git restore README.md

# Discard ALL uncommitted changes
git restore .

# Undo commit, keep changes staged
git reset --soft HEAD~1

# Undo commit, keep changes unstaged
git reset HEAD~1

# Undo commit AND discard changes
git reset --hard HEAD~1

# Nuclear option
# This throws away ALL local changes and makes your repository match GitHub exactly.
git fetch origin
git reset --hard origin/master
```
