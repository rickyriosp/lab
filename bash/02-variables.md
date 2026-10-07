# 2: Variables and Quoting

## 2.1 Variables

### Creating Variables

Critical Rule: no spaces around the ```=``` sign!
```bash
# WRONG - bash thinks "name" is a command
name = "DevOps"

# CORRECT
name="DevOps"
echo $name
```

This trips up everyone coming from other languages. Bash sees ```name = "DevOps"``` as "run the command ```name``` with arguments ```=``` and ```DevOps```".

### Accessing Variables

```bash
name="DevOps"
echo $name
echo ${name}
```

Both output ```DevOps```, but ```${}``` is clearer and safer.

```bash
# Problem: bash doesn't know where variable name ends
echo "$nameEngineer"
# Looks for variable "nameEngineer" - prints nothing

# Solution: braces make it clear
echo "${name}Engineer"
# DevOpsEngineer
```

**Best practice:** Always use ```${variable}``` in strings.

---

## 2.2 Quoting Rules - Security Critical

> "This is where most bash bugs come from. Get quoting wrong and you’ve got security holes."

### Double Quotes: Variables Expand

```bash
name="World"
echo "Hello, $name"
# Hello, World
```

Inside double quotes:
- Variables expand (```$name``` becomes ```World```)
- Spaces are preserved
- Most special characters are literal

### Single Quotes: Everything is Literal

```bash
name="World"
echo 'Hello, $name'
# Hello, $name
```

Inside single quotes:
- Nothing expands
- Everything is literal text


### No Quotes: Dangerous

```bash
message="Hello   World"
echo $message
# Hello World (spaces collapsed!)
echo "$message"
# Hello   World (spaces preserved)
```

### The Danger Demo

```bash
# Create a file with spaces
touch "my important file.txt"

# WRONG - tries to delete "my", "important", and "file.txt"
file="my important file.txt"
rm $file
# BREAKS!

# CORRECT - deletes the single file
rm "$file"
```

Unquoted variables undergo word splitting. One filename becomes three arguments. This is how you accidentally delete the wrong files.

---

## 2.3 Command Substitution

Capture command output into a variable

```bash
today=$(date +%Y-%m-%d)
echo "Today is $today"
```

Always use ```$()``` - it’s easier to read and can be nested.

```bash
# Store current directory
current_dir=$(pwd)

# Get git branch
branch=$(git branch --show-current)
```

The old backtick syntax command still works but ```$()``` is clearer and nestable. Use ```$()```.

---

## 2.4 Special Variables

### Script Arguments

```bash
echo "Script name: $0"
echo "First argument: $1"
echo "All arguments: $@"
echo "Number of arguments: $#"
```

```bash
./args hello world
```

Variable Meaning:
- ```$0```: Script name
- ```$1, $2, …```: Positional arguments
- ```$@```: All arguments (as separate words) - USE THIS
- ```$*```: All arguments (as single string) - AVOID
- ```$#```: Number of arguments

```$@``` vs ```$*```

```$@``` treats each argument as a separate quoted string. ```$*``` joins them into one string.

```bash
# If called with: ./script "hello world" "foo bar"

"$@" # Two arguments: "hello world" and "foo bar"
"$*" # One argument: "hello world foo bar"
```

Always use ```"$@"``` not ```"$*"``` when you need all arguments.

### Exit Status

```bash
ls /etc/passwd
echo "Exit code: $?"
# 0 (success)

ls /nonexistent
echo "Exit code: $?"
# 2 (failure)
```

- ```$?``` holds the exit code of the last command
- ```0``` = success, ```Non-zero``` = failure

Every command returns an exit code. This is how ```&&``` and ```||``` work. You’ll use this constantly.

### Other Special Variables

Variable Meaning:
- ```$?```: Exit code of last command
- ```$$```: Current shell’s process ID
- ```$USER```: Current username
- ```$HOME```: Home directory
- ```$PWD```: Current directory

---

## 2.5 Environment Variables

```bash
# Local variable - only in current shell
local_var="I'm local"

# Environment variable - inherited by child processes
export ENV_VAR="I'm exported"
```

Security Note: Environment variables are visible via ```/proc/<pid>/environ``` and ```ps e```. For secrets, use files with restricted permissions instead.

---

## Challenge: Dotfiles Info Script

Create ```~/dotfiles/info``` that displays information about your dotfiles setup:
1. Uses command substitution to count files in ~/dotfiles/
2. Uses special variables $USER and $HOME
3. Properly quotes a path that could contain spaces
4. Passes shellcheck

**Verify:**
```bash
shellcheck ~/dotfiles/info && chmod +x ~/dotfiles/info && ~/dotfiles/info
```

**Solution:**
```bash
#!/bin/bash

dotfiles_dir="$HOME/dotfiles"
file_count=$(ls "$dotfiles_dir" | wc -l)
today=$(date +%Y-%m-%d)

echo "=== Dotfiles Info ==="
echo "User: $USER"
echo "Dotfiles location: $dotfiles_dir"
echo "Files tracked: $file_count"
echo "Date: $today"
```
