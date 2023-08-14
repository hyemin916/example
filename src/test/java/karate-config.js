function fn() {
    const port = karate.properties['test.server.port'] ?? '8080';
    return {baseUrl: 'http://127.0.0.1:' + port};
}