// Copyright Yahoo. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.vespa.hosted.node.admin.task.util.file;

import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.Instant;
import java.util.Map;
import java.util.Set;

/**
 * This wrapper around PosixFileAttributes.
 *
 * @author hakonhall
 */
public record FileAttributes(Instant lastModifiedTime, int ownerId, int groupId, String permissions,
                             boolean isRegularFile, boolean isDirectory, long size) {

    @SuppressWarnings("unchecked")
    static FileAttributes fromAttributes(Map<String, Object> attributes) {
        return new FileAttributes(
                ((FileTime) attributes.get("lastModifiedTime")).toInstant(),
                (int) attributes.get("uid"),
                (int) attributes.get("gid"),
                PosixFilePermissions.toString(((Set<PosixFilePermission>) attributes.get("permissions"))),
                (boolean) attributes.get("isRegularFile"),
                (boolean) attributes.get("isDirectory"),
                (long) attributes.get("size"));
    }
}
