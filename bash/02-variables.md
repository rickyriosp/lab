
Creating Variables
```bash
# Rule: no spaces around the = sign
# WRONG - bash thinks "name" is a command
name = "DevOps"

# CORRECT
name="DevOps"
echo $name
```

### Accessing Variables
name="DevOps"
echo $name
echo ${name}

# Both output DevOps, but ${} is clearer and safer
# Problem: bash doesn't know where variable name ends
echo "$nameEngineer"
# Looks for variable "nameEngineer" - prints nothing

# Solution: braces make it clear
echo "${name}Engineer"
# DevOpsEngineer


### Quotes
# Double Quotes: variables expand, spaces preserved, special chars literal
name="World"
echo "Hello, $name"
# Hello, World

# Single Quote: everything is literal, nothing expands
name="World"
echo 'Hello, $name'
# Hello, $name

# Example
# Create a file with spaces
touch "my important file.txt"

# WRONG - tries to delete "my", "important", and "file.txt"
file="my important file.txt"
rm $file
# BREAKS!

# CORRECT - deletes the single file
rm "$file"


### Command Substitution
today=$(date +%Y-%m-%d)
echo "Today is $today"
# Store current directory
current_dir=$(pwd)

# Get git branch
branch=$(git branch --show-current)

### Script Arguments
echo "Script name: $0"
echo "First argument: $1"
echo "All arguments: $@"
echo "Number of arguments: $#"
# If called with: ./script "hello world" "foo bar"
"$@" # Two arguments: "hello world" and "foo bar"
"$*" # One argument: "hello world foo bar"


### Exit Status
ls /etc/passwd
echo "Exit code: $?"
# 0 (success)

ls /nonexistent
echo "Exit code: $?"
# 2 (failure)


### Environment Variables
# Local variable - only in current shell
local_var="I'm local"

# Environment variable - inherited by child processes
export ENV_VAR="I'm exported"


### Dotfiles example
dotfiles_dir="$HOME/dotfiles"
file_count=$(ls "$dotfiles_dir" | wc -l)
today=$(date +%Y-%m-%d)

echo "=== Dotfiles Info ==="
echo "User: $USER"
echo "Dotfiles location: $dotfiles_dir"
echo "Files tracked: $file_count"
echo "Date: $today"
