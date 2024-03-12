#include <jni.h>

#include <stdint.h>

#include <filesystem>
#include <string>
#include <vector>

using KtLong = int64_t;

namespace fs = std::filesystem;

static KtLong getModified(fs::path& path) {
    return fs::last_write_time(path).time_since_epoch().count();
}
